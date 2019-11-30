package cn.chenchl.basemvp.net;

import com.chenchl.common.net.bean.BaseModel;

import cn.chenchl.basemvp.main.bean.LoginBean;
import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * created by hasee on 2019/11/30
 **/
public interface MainNetService {
    /**
     * 登陆
     *
     * @param requestBody json传递
     * @return
     */
    @POST("login")
    Flowable<BaseModel<LoginBean>> login(@Body RequestBody requestBody);
}
