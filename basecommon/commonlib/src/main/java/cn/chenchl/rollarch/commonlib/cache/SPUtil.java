package cn.chenchl.rollarch.commonlib.cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import cn.chenchl.rollarch.commonlib.log.LogUtil;

/**
 * created by hasee on 2019/12/1
 **/
public class SPUtil implements ICache {
    private static final String TAG = SPUtil.class.getSimpleName();
    private static final String SP_NAME = "sp_data";
    private SharedPreferences sharedPreferences;

    private SPUtil() {
    }

    private static class Holder {
        private static SPUtil INSTANCE = new SPUtil();
    }

    public static SPUtil getInstance() {
        return SPUtil.Holder.INSTANCE;
    }

    @Override
    public void initCache(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void put(String key, Object value) {
        if (value instanceof Integer) {
            getEditor().putInt(key, (Integer) value).apply();
        } else if (value instanceof Long) {
            getEditor().putLong(key, (Long) value).apply();
        } else if (value instanceof Float) {
            getEditor().putFloat(key, (Float) value).apply();
        } else if (value instanceof Boolean) {
            getEditor().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof String) {
            getEditor().putString(key, (String) value).apply();
        } else if (value instanceof Set) {
            getEditor().putStringSet(key, (Set<String>) value).apply();
        } else {
            throw new IllegalArgumentException("sharedPreferences don't support this type");
        }
        LogUtil.i(TAG, "sp put :" + key + " result = " + value);
    }

    @Override
    public <T> T get(String key, T defValue) {
        if (sharedPreferences == null) {
            throw new NullPointerException("sharedPreferences is Null,call initcache plz");
        }
        Object result = null;
        if (defValue instanceof Integer) {
            result = sharedPreferences.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Long) {
            result = sharedPreferences.getLong(key, (Long) defValue);
        } else if (defValue instanceof Float) {
            result = sharedPreferences.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Boolean) {
            result = sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof String) {
            result = sharedPreferences.getString(key, (String) defValue);
        } else if (defValue instanceof Set) {
            result = sharedPreferences.getStringSet(key, (Set<String>) defValue);
        } else {
            throw new IllegalArgumentException("sharedPreferences don't support this type");
        }
        LogUtil.i(TAG, "sp get :" + key + " result = " + result);
        return (T) result;
    }

    @Override
    public void remove(String key) {
        getEditor().remove(key).apply();
        LogUtil.i(TAG, "sp remove :" + key + " key = " + key);
    }

    @Override
    public boolean contains(String key) {
        if (sharedPreferences == null) {
            throw new NullPointerException("sharedPreferences is Null,call initcache plz");
        }
        LogUtil.i(TAG, "sp contains :" + key + " key = " + key);
        return sharedPreferences.contains(key);
    }

    @Override
    public void clear() {
        getEditor().clear().apply();
        LogUtil.i(TAG, "sp clear");
    }

    private SharedPreferences.Editor getEditor() {
        if (sharedPreferences == null) {
            throw new NullPointerException("sharedPreferences is Null,call initcache plz");
        }
        return sharedPreferences.edit();
    }
}
