package cn.chenchl.basemvp.main;

import com.chenchl.mvp.interfaces.BasePresenter;

import cn.chenchl.rollarch.commonlib.widget.ToastUtil;

/**
 * created by hasee on 2019/11/27
 **/
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private String title;
    private MainModel mainModel;

    @Override
    public void init() {
        mainModel = new MainModel(this);
    }

    @Override
    public void destory() {
        mainModel = null;
    }

    public MainPresenter() {
    }

    @Override
    public void initData(String s) {
        title = s;
    }

    @Override
    public void doSomething() {
        /*Flowable.timer(3000, TimeUnit.MILLISECONDS)
                .compose(RxJavaTransformers.getDefaultScheduler())
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "延时" + aLong;
                    }
                })
                .as(RxLifecycleUtil.bindLifeCycle(mlifeCycleOwner, Lifecycle.Event.ON_PAUSE))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        title += mainModel.getText(title);
                        getV().setText(title);
                        getV().showToast("166666");
                    }
                });*/
        mainModel.login("xlm", "9999s99");
    }

    @Override
    public void showResult(String str) {
        ToastUtil.showShort(str);
    }
}
