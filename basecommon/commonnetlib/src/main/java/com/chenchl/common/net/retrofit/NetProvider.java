package com.chenchl.common.net.retrofit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by wanglei on 2016/12/24.
 */

public interface NetProvider {
    Interceptor[] configInterceptors();

    void configHttps(OkHttpClient.Builder builder);

    CookieJar configCookie();

    long configConnectTimeoutMills();

    long configReadTimeoutMills();

    long configWriteTimeoutMills();

    boolean configLogEnable();

    boolean handleError(NetError error);
}
