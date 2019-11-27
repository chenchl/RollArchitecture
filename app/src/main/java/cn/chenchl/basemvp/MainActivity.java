package cn.chenchl.basemvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    private TextView tvText;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tv_text);
        /*ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        TestProxy testProxy = str -> System.out.println("sdad " + str);
        TestProxyHandler testProxyHandler = new TestProxyHandler(testProxy);
        TestProxy o = (TestProxy) Proxy.newProxyInstance(testProxy.getClass().getClassLoader(), testProxy.getClass().getInterfaces(), testProxyHandler);
        o.sayTest("123");
        Disposable haha = Flowable.timer(1, TimeUnit.MINUTES)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show());
        haha.dispose();*/
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
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detchView();
    }

    @Override
    public void setText(String text) {
        tvText.setText(text);
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
