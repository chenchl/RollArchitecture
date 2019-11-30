package com.chenchl.mvp.interfaces;

/**
 * created by hasee on 2019/11/30
 **/
public abstract class BaseModel<P extends BasePresenter> {

    private P presenter;

    public BaseModel(P presenter) {
        this.presenter = presenter;
    }

    public void setP(P presenter) {
        this.presenter = presenter;
    };

    public P getP() {
        return presenter;
    }

}
