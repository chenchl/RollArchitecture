package cn.chenchl.rollarch.commonlib.rxjava;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * created by hasee on 2019/11/29
 * autodispose封装
 **/
public class RxLifecycleUtil {

    private RxLifecycleUtil() {

    }

    public static <T> AutoDisposeConverter<T> bindLifeCycle(LifecycleOwner lifecycleOwner) {
        return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(lifecycleOwner));
    }

    public static <T> AutoDisposeConverter<T> bindLifeCycle(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(lifecycleOwner, event));
    }
}
