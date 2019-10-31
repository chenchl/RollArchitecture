package cn.chenchl.rollarch.componentservice.services.component_login;

import android.app.Activity;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * created by ccl on 2019/4/17
 **/
public interface LoginService extends IProvider {
    String getString(String name);

    void loginOut();

    void loginIn(Activity activity, onLoginListener loginListener);

    public interface onLoginListener {
        void success();

        void fail(int code, String msg);
    }
}
