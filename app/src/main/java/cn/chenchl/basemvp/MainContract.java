package cn.chenchl.basemvp;

import com.chenchl.mvp.IView;

import androidx.annotation.UiThread;

/**
 * created by hasee on 2019/11/27
 **/
public interface MainContract {

    interface Model {
        String getText(String name);
    }

    interface Presenter {
        void init(String s);

        void doSomething();
    }

    interface View extends IView<Presenter> {
        @UiThread
        void showToast(String s);

        @UiThread
        void setText(String text);
    }
}
