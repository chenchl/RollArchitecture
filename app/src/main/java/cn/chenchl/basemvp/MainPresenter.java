package cn.chenchl.basemvp;

import com.chenchl.mvp.interfaces.BasePresenter;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.Lifecycle;
import cn.chenchl.rollarch.commonlib.rxjava.RxJavaTransformers;
import cn.chenchl.rollarch.commonlib.rxjava.RxLifecycleUtil;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * created by hasee on 2019/11/27
 **/
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

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
        Flowable.timer(3000, TimeUnit.MILLISECONDS)
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
                });
    }
}
