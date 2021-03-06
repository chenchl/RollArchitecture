package cn.chenchl.basemvp.main;

import com.chenchl.mvp.interfaces.BasePresenter;

import cn.chenchl.rollarch.commonlib.cache.LocalDataProxy;

/**
 * created by hasee on 2019/11/27
 **/
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainModel mainModel;

    @Override
    public void init() {
        mainModel = new MainModel(this);
    }

    @Override
    public void destory() {
        mainModel = null;
    }

    @Override
    public void doSomething() {
        mainModel.login("xlm", "999999");
    }

    @Override
    public void showResult(String str) {
        getV().showToast(str);
        getV().setText(str);
        LocalDataProxy.getInstance().put("logindata",str);
    }
}
