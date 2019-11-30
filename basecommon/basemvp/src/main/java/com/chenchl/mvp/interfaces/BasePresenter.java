package com.chenchl.mvp.interfaces;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

/**
 * created by hasee on 2019/11/27
 **/
public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V mView;
    protected LifecycleOwner mlifeCycleOwner;

    @Override
    public void attchView(V view) {
        this.mView = view;
        if (view instanceof Activity || view instanceof Fragment) {//针对activity和fragment自动设置autodispose
            setlifeCycleOwner((LifecycleOwner) view);
        }
        init();
    }

    @Override
    public void detchView() {
        this.mView = null;
        mlifeCycleOwner = null;
        destory();
    }

    @Override
    public abstract void destory();

    @Override
    public void setlifeCycleOwner(LifecycleOwner mlifeCycleOwner) {
        this.mlifeCycleOwner = mlifeCycleOwner;
    }

    public LifecycleOwner getlifeCycleOwner() {
        return mlifeCycleOwner;
    }

    /**
     * View是否绑定
     *
     * @return
     */
    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public V getV() {
        if (mView == null) {
            throw new IllegalStateException("V can not be null");
        }
        return mView;
    }
}
