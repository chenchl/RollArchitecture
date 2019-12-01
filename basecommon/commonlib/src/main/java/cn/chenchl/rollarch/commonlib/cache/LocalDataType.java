package cn.chenchl.rollarch.commonlib.cache;

import android.content.Context;

/**
 * share本地类
 */
public enum LocalDataType {

    APP_SP_DEFAULT("AppSpDefault", Context.MODE_PRIVATE);

    LocalDataType(String key, int mode) {
        this.key = key;
        this.mode = mode;
    }

    private String key;
    private int mode;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
