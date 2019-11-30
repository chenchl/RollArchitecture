package cn.chenchl.rollarch.commonlib.log;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.BufferedReader;
import java.io.FileReader;

import androidx.annotation.Nullable;
import cn.chenchl.rollarch.commonlib.Utils;

/**
 * created by ccl on 2019/2/14
 **/
public class LogUtil {

    public static boolean isDebug = true; // LOG开关

    private static boolean getIsLogEnable(Context context) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().
                    getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            int ENVIRONMENT = appInfo.metaData.getInt("LOG_ENABLE");
            return ENVIRONMENT == 1;
        } catch (Exception e) {
            return true;
        }
    }

    //初始化logger
    public static void init(Context context) {
        try {
            // 获取当前包名
            String packageName = context.getApplicationContext().getPackageName();
            // 获取当前进程名
            String processName = getProcessName(android.os.Process.myPid());
            if (processName == null || processName.equals(packageName)) {//保证只在主线程初始化一次
                //打印到logcat
                PrettyFormatStrategy logcatFormatStrategy = PrettyFormatStrategy.newBuilder()
                        .tag(getApplicationName())
                        .build();
                Logger.addLogAdapter(new AndroidLogAdapter(logcatFormatStrategy) {
                    @Override
                    public boolean isLoggable(int priority, @Nullable String tag) {
                        return isDebug;
                    }
                });
                LogUtil.e("logger", "init success test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = Utils.getApp().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(Utils.getApp().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
            return "test";
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            if (!TextUtils.isEmpty(msg)) {
                Logger.d(tag + "---" + msg);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            if (!TextUtils.isEmpty(msg)) {
                Logger.e(tag + "---" + msg);
            }
        }
    }

    public static void d(Object object, String msg) {
        if (isDebug) {
            if (!TextUtils.isEmpty(msg)) {
                Logger.d(msg);
            }
        }
    }

    public static void e(Object object, String msg) {
        if (isDebug) {
            if (!TextUtils.isEmpty(msg)) {
                Logger.e(msg);
            }
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            if (!TextUtils.isEmpty(msg)) {
                Logger.i(tag + "---" + msg);
            }
        }
    }

    public static void i(Object object, String msg) {
        if (isDebug) {
            if (!TextUtils.isEmpty(msg)) {
                Logger.i(msg);
            }
        }
    }

    public static void json(String msg) {
        if (isDebug) {
            if (!TextUtils.isEmpty(msg)) {
                Logger.json(msg);
            }
        }
    }
}
