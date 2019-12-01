package cn.chenchl.basemvp.main;

import android.os.Build;

import com.chenchl.common.net.retrofit.DefaultResponseSubscriber;
import com.chenchl.common.net.retrofit.NetError;
import com.chenchl.common.net.retrofit.RetrofitUtil;
import com.chenchl.mvp.interfaces.BaseModel;

import org.json.JSONObject;

import androidx.lifecycle.Lifecycle;
import cn.chenchl.basemvp.main.bean.LoginBean;
import cn.chenchl.basemvp.net.MainApi;
import cn.chenchl.rollarch.commonlib.codec.MD5Utils;
import cn.chenchl.rollarch.commonlib.rxjava.RxLifecycleUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * created by hasee on 2019/11/27
 **/
public class MainModel extends BaseModel<MainPresenter> implements MainContract.Model {


    public MainModel(MainPresenter presenter) {
        super(presenter);
    }

    @Override
    public void login(String userName, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("loginName", userName);
            jsonObject.put("deviceNumber", "23232323");
            jsonObject.put("appVersion", "4.3.1.191101");
            jsonObject.put("iccid", "12312312312sd");
            jsonObject.put("password", MD5Utils.MD5(password.toLowerCase()));
            jsonObject.put("deviceName", Build.FINGERPRINT);
            jsonObject.put("isForce", "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        MainApi.getMainNetService()
                .login(requestBody)
                .compose(RetrofitUtil.getDefaultTransformer())
                //.compose(RxJavaTransformers.getDefaultScheduler())
                .as(RxLifecycleUtil.bindLifeCycle(getP().getlifeCycleOwner(), Lifecycle.Event.ON_PAUSE))
                .subscribe(new DefaultResponseSubscriber<com.chenchl.common.net.bean.BaseModel<LoginBean>, LoginBean>() {
                    @Override
                    protected void onSuccess(LoginBean data) {
                        getP().showResult(data.toString());
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getP().showResult(error.getMessage());
                    }
                });
    }

}
