package com.chenchl.common.net.retrofit;

import com.chenchl.common.net.bean.BaseModel;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.net.UnknownHostException;

import io.reactivex.subscribers.ResourceSubscriber;


/**
 * Created by wanglei on 2016/12/26.
 */

public abstract class DefaultResponseSubscriber<T extends BaseModel, D> extends ResourceSubscriber<T> {

    @Override
    public void onNext(T t) {
        BaseModel model = t;
        switch (model.getCode()) {
            case NetError.Success:
                onSuccess((D) model.getData());
                break;
            default:
                onFail(new NetError(new Exception("未知错误"), NetError.OtherError));
                break;
        }
    }

    @Override
    public void onError(Throwable e) {
        NetError error = null;
        if (e != null) {
            if (!(e instanceof NetError)) {
                if (e instanceof UnknownHostException) {
                    error = new NetError(e, NetError.NoConnectError);
                } else if (e instanceof JSONException
                        || e instanceof JsonParseException
                        || e instanceof JsonSyntaxException) {
                    error = new NetError(e, NetError.ParseError);
                } else {
                    error = new NetError(e, NetError.OtherError);
                }
            } else {
                error = (NetError) e;
            }

            if (useCommonErrorHandler()
                    && RetrofitUtil.getCommonProvider() != null) {
                if (RetrofitUtil.getCommonProvider().handleError(error)) {        //使用通用异常处理
                    return;
                }
            }
            onFail(error);
        }

    }

    protected abstract void onSuccess(D data);

    protected abstract void onFail(NetError error);

    @Override
    public void onComplete() {

    }


    protected boolean useCommonErrorHandler() {
        return false;
    }


}
