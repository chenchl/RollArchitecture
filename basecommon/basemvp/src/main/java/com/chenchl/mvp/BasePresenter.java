package com.chenchl.mvp;

/**
 * created by hasee on 2019/11/27
 **/
public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V mView;

    @Override
    public void attchView(V view) {
        this.mView = view;
    }

    @Override
    public void detchView() {
        this.mView = null;
        destory();
    }

    @Override
    public abstract void destory();

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
