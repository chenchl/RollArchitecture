package cn.chenchl.basemvp;

import com.chenchl.mvp.BasePresenter;

/**
 * created by hasee on 2019/11/27
 **/
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    private String title;
    private MainModel mainModel;

    @Override
    public void destory() {

    }
    @Override
    public void init(String s) {
        title = s;
        mainModel = new MainModel();
    }
    @Override
    public void doSomething() {
        title += mainModel.getText(title);
        getV().setText(title);
        getV().showToast("166666");
    }
}
