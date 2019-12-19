package cn.chenchl.rollarch.commonlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.lifecycle.LiveData;
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
