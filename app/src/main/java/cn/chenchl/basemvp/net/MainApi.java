package cn.chenchl.basemvp.net;

import com.chenchl.common.net.retrofit.RetrofitUtil;

/**
 * created by hasee on 2019/11/30
 **/
public class MainApi {

    public static final String API_BASE_URL = "https://readapp.tope365.com/engApi/";

    private static volatile MainNetService mainNetService;

    public static MainNetService getMainNetService() {
        if (mainNetService == null) {
            synchronized (MainApi.class) {
                if (mainNetService == null) {
                    mainNetService = RetrofitUtil.getInstance().getRetrofit(API_BASE_URL, true).create(MainNetService.class);
                }
            }
        }
        return mainNetService;
    }
}
