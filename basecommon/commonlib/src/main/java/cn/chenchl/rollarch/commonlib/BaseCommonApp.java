package cn.chenchl.rollarch.commonlib;

import android.os.Build;
import android.os.Process;

import androidx.multidex.MultiDexApplication;
import cn.chenchl.rollarch.commonlib.image.ImageLoaderUtil;


/**
 * Created by yang on 2018/9/7.
 */
public class BaseCommonApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        checkAppReplacingState();
    }

    /**
     * Attempt to invoke virtual method 'android.content.res.AssetManager android.content.res.Resources.getAssets()' on a null object reference 问题修复 针对5.1以下机器
     */
    private void checkAppReplacingState() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            if (getResources() == null) {
                try {
                    Process.killProcess(Process.myPid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoaderUtil.getInstance().clearMemoryCache(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            ImageLoaderUtil.getInstance().clearMemoryCache(this);
        }
        ImageLoaderUtil.getInstance().trimMemory(this, level);
    }
}
