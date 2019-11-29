package cn.chenchl.basemvp;

import android.view.View;
import android.widget.TextView;

import com.chenchl.mvp.base.BaseMvpActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import butterknife.BindView;

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
        mainPresenter.init(tvText.getText().toString());
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.doSomething();
            }
        });
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
