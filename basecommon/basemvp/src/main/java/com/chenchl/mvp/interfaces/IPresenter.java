package com.chenchl.mvp.interfaces;

import androidx.annotation.UiThread;
import androidx.lifecycle.LifecycleOwner;

/**
 * created by hasee on 2019/11/27
 **/
public interface IPresenter<V> {

    @UiThread
    void attchView(V view);

    @UiThread
    void detchView();

    boolean isViewAttached();

    void setlifeCycleOwner(LifecycleOwner mlifeCycleOwner);

    void destory();

    V getV();
}
