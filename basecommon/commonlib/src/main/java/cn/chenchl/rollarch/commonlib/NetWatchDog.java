package cn.chenchl.rollarch.commonlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import cn.chenchl.rollarch.commonlib.log.LogUtil;

/**
 * created by ccl on 2019/12/18
 * livedata 实现netwatchdog
 **/
public class NetWatchDog extends LiveData<Integer> {

    @IntDef({NetState.WIFI, NetState.MOBILE, NetState.NOCONTECTED})
    @Retention(RetentionPolicy.CLASS)
    public @interface NetState {
        int NOCONTECTED = 0;
        int WIFI = 1;
        int MOBILE = 2;
    }

    private IntentFilter intentFilter;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                //获取手机的连接服务管理器，这里是连接管理器类
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo wifiNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobileNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                NetworkInfo.State wifiState = NetworkInfo.State.UNKNOWN;
                NetworkInfo.State mobileState = NetworkInfo.State.UNKNOWN;

                if (wifiNetworkInfo != null) {
                    wifiState = wifiNetworkInfo.getState();
                }
                if (mobileNetworkInfo != null) {
                    mobileState = mobileNetworkInfo.getState();
                }

                if (NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED == mobileState) {
                    LogUtil.d("VideoNetWatchdog", "onWifiTo4G()");
                    setValue(NetState.MOBILE);
                } else if (NetworkInfo.State.CONNECTED == wifiState && NetworkInfo.State.CONNECTED != mobileState) {
                    LogUtil.d("VideoNetWatchdog", "on4GToWifi()");
                    setValue(NetState.WIFI);
                } else if (NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED != mobileState) {
                    LogUtil.d("VideoNetWatchdog", "onNetDisconnected()");
                    setValue(NetState.NOCONTECTED);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private NetWatchDog() {
        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    private static class Holder {
        private static NetWatchDog instance = new NetWatchDog();
    }

    public static NetWatchDog getInstance() {
        return Holder.instance;
    }

    @Override
    protected void onActive() {
        super.onActive();
        try {
            Utils.getApp().registerReceiver(mReceiver, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        try {
            Utils.getApp().unregisterReceiver(mReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super Integer> observer) {
        super.observe(owner, observer);
        //hook防止第一次注册监听时数据倒灌
        hook(observer);
    }

    private void hook(Observer<? super Integer> observer) {
        Class<LiveData> liveDataClass = LiveData.class;
        try {
            //获取field private SafeIterableMap<Observer<T>, ObserverWrapper> mObservers
            Field mObservers = liveDataClass.getDeclaredField("mObservers");
            mObservers.setAccessible(true);
            //获取SafeIterableMap集合mObservers
            Object observers = mObservers.get(this);
            Class<?> observersClass = observers.getClass();
            //获取SafeIterableMap的get(Object obj)方法
            Method methodGet = observersClass.getDeclaredMethod("get", Object.class);
            methodGet.setAccessible(true);
            //获取到observer在集合中对应的ObserverWrapper对象
            Object objectWrapperEntry = methodGet.invoke(observers, observer);
            Object objectWrapper = null;
            if (objectWrapperEntry instanceof Map.Entry) {
                objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
            }
            if (objectWrapper == null) {
                throw new NullPointerException("ObserverWrapper can not be null");
            }
            //获取ObserverWrapper的Class对象  LifecycleBoundObserver extends ObserverWrapper
            Class<?> wrapperClass = objectWrapper.getClass().getSuperclass();
            //获取ObserverWrapper的field mLastVersion
            Field mLastVersion = wrapperClass.getDeclaredField("mLastVersion");
            mLastVersion.setAccessible(true);
            //获取liveData的field mVersion
            Field mVersion = liveDataClass.getDeclaredField("mVersion");
            mVersion.setAccessible(true);
            Object mV = mVersion.get(this);
            //把当前ListData的mVersion赋值给 ObserverWrapper的field mLastVersion
            mLastVersion.set(objectWrapper, mV);

            mObservers.setAccessible(false);
            methodGet.setAccessible(false);
            mLastVersion.setAccessible(false);
            mVersion.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 静态方法获取是否有网络连接
     *
     * @param context 上下文
     * @return 是否连接
     */
    public static boolean hasNet(Context context) {
        //获取手机的连接服务管理器，这里是连接管理器类
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        NetworkInfo.State wifiState = NetworkInfo.State.UNKNOWN;
        NetworkInfo.State mobileState = NetworkInfo.State.UNKNOWN;

        if (wifiNetworkInfo != null) {
            wifiState = wifiNetworkInfo.getState();
        }
        if (mobileNetworkInfo != null) {
            mobileState = mobileNetworkInfo.getState();
        }

        if (NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED != mobileState) {
            return false;
        }

        return true;
    }

    /**
     * 静态判断是不是4G网络
     *
     * @param context 上下文
     * @return 是否是4G
     */
    public static boolean is4GConnected(Context context) {
        //获取手机的连接服务管理器，这里是连接管理器类
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo mobileNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        NetworkInfo.State mobileState = NetworkInfo.State.UNKNOWN;

        if (mobileNetworkInfo != null) {
            mobileState = mobileNetworkInfo.getState();
        }

        return NetworkInfo.State.CONNECTED == mobileState;
    }
}
