package cn.chenchl.basemvp;

import android.os.Bundle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import androidx.appcompat.app.AppCompatActivity;
import cn.chenchl.TestProxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        TestProxy testProxy = str -> System.out.println("sdad " + str);
        TestProxyHandler testProxyHandler = new TestProxyHandler(testProxy);
        TestProxy o = (TestProxy) Proxy.newProxyInstance(testProxy.getClass().getClassLoader(), testProxy.getClass().getInterfaces(), testProxyHandler);
        o.sayTest("123");
    }

    class TestProxyHandler implements InvocationHandler {

        private Object obj;

        public TestProxyHandler(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(obj,args);
        }
    }
}
