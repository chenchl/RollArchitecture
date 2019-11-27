package com.chenchl.mvp;

import androidx.annotation.UiThread;

/**
 * created by hasee on 2019/11/27
 **/
public interface IPresenter<V> {

    @UiThread
    void attchView(V view);

    @UiThread
    void detchView();

    boolean isViewAttached();

    void destory();

    V getV();
}
