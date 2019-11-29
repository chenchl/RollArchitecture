package cn.chenchl.rollarch.commonlib.rxjava;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by hasee on 2019/11/29
 * Rxjava常用compose Transformer
 **/
public class RxJavaTransformers {

    /**
     * 线程切换
     *
     * @return
     */
    public static <T> FlowableTransformer<T, T> getDefaultScheduler() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
