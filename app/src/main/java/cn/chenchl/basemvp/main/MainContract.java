package cn.chenchl.basemvp.main;

import com.chenchl.mvp.interfaces.IView;

import androidx.annotation.UiThread;

/**
 * created by hasee on 2019/11/27
 **/
public interface MainContract {

    interface Model {
        void login(String userName, String password);
    }

    interface Presenter {

        void doSomething();

        void showResult(String str);
    }

    interface View extends IView<Presenter> {
        @UiThread
        void showToast(String s);

        @UiThread
        void setText(String text);
    }
}
