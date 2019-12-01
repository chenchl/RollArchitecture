package cn.chenchl.basemvp.main;

import android.view.View;
import android.widget.TextView;

import com.chenchl.common.net.retrofit.NetError;
import com.chenchl.common.net.retrofit.NetProvider;
import com.chenchl.common.net.retrofit.RetrofitUtil;
import com.chenchl.mvp.base.BaseMvpActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import butterknife.BindView;
import cn.chenchl.basemvp.R;
import cn.chenchl.rollarch.commonlib.Utils;
import cn.chenchl.rollarch.commonlib.cache.LocalDataProxy;
import cn.chenchl.rollarch.commonlib.cache.SPUtil;
import cn.chenchl.rollarch.commonlib.log.LogUtil;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class MainActivity extends BaseMvpActivity implements MainContract.View {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_text)
    TextView tvText;
    private MainPresenter mainPresenter;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        TestProxy testProxy = str -> System.out.println("sdad " + str);
        TestProxyHandler testProxyHandler = new TestProxyHandler(testProxy);
        TestProxy o = (TestProxy) Proxy.newProxyInstance(testProxy.getClass().getClassLoader(), testProxy.getClass().getInterfaces(), testProxyHandler);
        o.sayTest("123");
        Disposable haha = Flowable.timer(1, TimeUnit.MINUTES)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show());
        haha.dispose();

    }*/

    @Override
    public void setText(String text) {
        tvText.setText(text);
    }

    @Override
    public void initBefore() {
        LogUtil.init(Utils.getApp());
        RetrofitUtil.registerProvider(new NetProvider() {
            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public long configWriteTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }
        });
        //LocalDataProxy.getInstance().initCache(getApplicationContext());
        LocalDataProxy.getInstance().initCache(SPUtil.getInstance(),getApplicationContext());
    }

    @Override
    public int setXMLView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initdata() {
        mainPresenter = new MainPresenter();
        mainPresenter.attchView(this);
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.doSomething();
            }
        });
        tvText.setText(LocalDataProxy.getInstance().get("logindata","no login"));
    }

    @Override
    public void destoryP() {
        mainPresenter.detchView();
    }

    @Override
    public void refresh() {

    }

    @Override
    public String setTag() {
        return "主页";
    }

    class TestProxyHandler implements InvocationHandler {

        private Object obj;

        public TestProxyHandler(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(obj, args);
        }
    }
}
