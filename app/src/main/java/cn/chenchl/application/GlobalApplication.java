package cn.chenchl.application;

import com.chenchl.common.net.retrofit.NetError;
import com.chenchl.common.net.retrofit.NetProvider;
import com.chenchl.common.net.retrofit.RetrofitUtil;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.chenchl.rollarch.commonlib.BaseCommonApp;
import cn.chenchl.rollarch.commonlib.Utils;
import cn.chenchl.rollarch.commonlib.cache.LocalDataProxy;
import cn.chenchl.rollarch.commonlib.log.LogUtil;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * created by hasee on 2019/12/2
 **/
public class GlobalApplication extends BaseCommonApp {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init(Utils.getApp());
        RetrofitUtil.registerProvider(new NetProvider() {
            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public long configWriteTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }
        });
        LocalDataProxy.getInstance().initCache(getApplicationContext());
        //LocalDataProxy.getInstance().initCache(SPUtil.getInstance(),getApplicationContext());
    }


}
