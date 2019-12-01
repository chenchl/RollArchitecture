package cn.chenchl.rollarch.commonlib.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.tencent.mmkv.MMKV;

import cn.chenchl.rollarch.commonlib.log.LogUtil;

/**
 * created by ccl on 2019/2/14
 **/
public class MMKVUtil implements ICache {
    private static final String TAG = MMKVUtil.class.getSimpleName();

    private MMKVUtil() {

    }

    private static class Holder {
        private static MMKVUtil INSTANCE = new MMKVUtil();
    }

    public static MMKVUtil getInstance() {
        return MMKVUtil.Holder.INSTANCE;
    }

    /**
     * MMKV初始化
     *
     * @param context 默认为file/mmkv
     */
    public void initMMKV(Context context) {
        String rootDir = MMKV.initialize(context);
        LogUtil.i(TAG, "mmkv root: " + rootDir);
    }

    /**
     * MMKV初始化
     *
     * @param dir 自定义存储路径
     */
    public void initMMKV(String dir) {
        String rootDir = MMKV.initialize(dir);
        LogUtil.i(TAG, "mmkv root: " + rootDir);
    }

    @Override
    public void initCache(Context context) {
        initMMKV(context);
    }

    /**
     * 存储数据
     *
     * @param mmapID
     * @param mode
     * @param key
     * @param value
     */
    public void setInternal(String mmapID, int mode, String key, Object value) {
        MMKV kv = MMKV.mmkvWithID(mmapID, mode);
        boolean result = false;
        if (value instanceof Integer) {
            result = kv.encode(key, (Integer) value);
        } else if (value instanceof Long) {
            result = kv.encode(key, (Long) value);
        } else if (value instanceof Float) {
            result = kv.encode(key, (Float) value);
        } else if (value instanceof Double) {
            result = kv.encode(key, (Double) value);
        } else if (value instanceof Boolean) {
            result = kv.encode(key, (Boolean) value);
        } else if (value instanceof String) {
            result = kv.encode(key, (String) value);
        } else if (value instanceof byte[]) {
            result = kv.encode(key, (byte[]) value);
        }
        LogUtil.i(TAG, "mmkv " + mmapID + " setInternal :" + key + " " + value + " result = " + result);
    }

    /**
     * 存储数据
     *
     * @param mmapID
     * @param key
     * @param value
     */
    public void setInternal(String mmapID, String key, Object value) {
        MMKV kv = MMKV.mmkvWithID(mmapID, Context.MODE_PRIVATE);
        boolean result = false;
        if (value instanceof Integer) {
            result = kv.encode(key, (Integer) value);
        } else if (value instanceof Long) {
            result = kv.encode(key, (Long) value);
        } else if (value instanceof Float) {
            result = kv.encode(key, (Float) value);
        } else if (value instanceof Double) {
            result = kv.encode(key, (Double) value);
        } else if (value instanceof Boolean) {
            result = kv.encode(key, (Boolean) value);
        } else if (value instanceof String) {
            result = kv.encode(key, (String) value);
        } else if (value instanceof byte[]) {
            result = kv.encode(key, (byte[]) value);
        }
        LogUtil.i(TAG, "mmkv " + mmapID + " setInternal :" + key + " " + value + " result = " + result);
    }

    /**
     * 存储数据
     *
     * @param key
     * @param value
     */
    private void setInternal(String key, Object value) {
        MMKV kv = MMKV.defaultMMKV();
        boolean result = false;
        if (value instanceof Integer) {
            result = kv.encode(key, (Integer) value);
        } else if (value instanceof Long) {
            result = kv.encode(key, (Long) value);
        } else if (value instanceof Float) {
            result = kv.encode(key, (Float) value);
        } else if (value instanceof Double) {
            result = kv.encode(key, (Double) value);
        } else if (value instanceof Boolean) {
            result = kv.encode(key, (Boolean) value);
        } else if (value instanceof String) {
            result = kv.encode(key, (String) value);
        } else if (value instanceof byte[]) {
            result = kv.encode(key, (byte[]) value);
        }
        LogUtil.i(TAG, "mmkv setInternal :" + key + " " + value + " result = " + result);
    }

    /**
     * 获取数据
     *
     * @param mmapID
     * @param mode
     * @param key
     * @param defValue
     * @param <T>
     * @return
     */
    public <T> T getInternal(String mmapID, int mode, String key, T defValue) {
        MMKV kv = MMKV.mmkvWithID(mmapID, mode);
        Object result = null;
        if (defValue instanceof Integer) {
            result = kv.decodeInt(key, (Integer) defValue);
        } else if (defValue instanceof Long) {
            result = kv.decodeLong(key, (Long) defValue);
        } else if (defValue instanceof Float) {
            result = kv.decodeFloat(key, (Float) defValue);
        } else if (defValue instanceof Double) {
            result = kv.decodeDouble(key, (Double) defValue);
        } else if (defValue instanceof Boolean) {
            result = kv.decodeBool(key, (Boolean) defValue);
        } else if (defValue instanceof String) {
            result = kv.decodeString(key, (String) defValue);
        } else if (defValue instanceof byte[]) {
            result = kv.decodeBytes(key);
        }
        LogUtil.i(TAG, "mmkv getInternal :" + key + " result = " + result);
        return (T) result;
    }

    /**
     * 获取数据
     *
     * @param mmapID
     * @param key
     * @param defValue
     * @param <T>
     * @return
     */
    public <T> T getInternal(String mmapID, String key, T defValue) {
        MMKV kv = MMKV.mmkvWithID(mmapID, Context.MODE_PRIVATE);
        Object result = null;
        if (defValue instanceof Integer) {
            result = kv.decodeInt(key, (Integer) defValue);
        } else if (defValue instanceof Long) {
            result = kv.decodeLong(key, (Long) defValue);
        } else if (defValue instanceof Float) {
            result = kv.decodeFloat(key, (Float) defValue);
        } else if (defValue instanceof Double) {
            result = kv.decodeDouble(key, (Double) defValue);
        } else if (defValue instanceof Boolean) {
            result = kv.decodeBool(key, (Boolean) defValue);
        } else if (defValue instanceof String) {
            result = kv.decodeString(key, (String) defValue);
        } else if (defValue instanceof byte[]) {
            result = kv.decodeBytes(key);
        }
        LogUtil.i(TAG, "mmkv getInternal :" + key + " result = " + result);
        return (T) result;
    }

    /**
     * 获取数据
     *
     * @param key
     * @param defValue
     * @param <T>
     * @return
     */
    private <T> T getInternal(String key, T defValue) {
        MMKV kv = MMKV.defaultMMKV();
        Object result = null;
        if (defValue instanceof Integer) {
            result = kv.decodeInt(key, (Integer) defValue);
        } else if (defValue instanceof Long) {
            result = kv.decodeLong(key, (Long) defValue);
        } else if (defValue instanceof Float) {
            result = kv.decodeFloat(key, (Float) defValue);
        } else if (defValue instanceof Double) {
            result = kv.decodeDouble(key, (Double) defValue);
        } else if (defValue instanceof Boolean) {
            result = kv.decodeBool(key, (Boolean) defValue);
        } else if (defValue instanceof String) {
            result = kv.decodeString(key, (String) defValue);
        } else if (defValue instanceof byte[]) {
            result = kv.decodeBytes(key);
        }
        LogUtil.i(TAG, "mmkv getInternal :" + key + " result = " + result);
        return (T) result;
    }

    /**
     * 删除指定key的数据
     *
     * @param mmapID
     * @param key
     */
    public void removeInternal(String mmapID, String key) {
        MMKV kv = MMKV.mmkvWithID(mmapID, Context.MODE_PRIVATE);
        kv.removeValueForKey(key);
        LogUtil.i(TAG, "mmkv " + mmapID + " remove :" + key);
    }

    /**
     * 删除指定key的数据
     *
     * @param key
     */
    private void removeInternal(String key) {
        MMKV kv = MMKV.defaultMMKV();
        kv.removeValueForKey(key);
        LogUtil.i(TAG, "mmkv removeInternal :" + key);
    }

    /**
     * 判断指定key是否存在
     *
     * @param key
     */
    private boolean containsInternal(String key) {
        MMKV kv = MMKV.defaultMMKV();
        boolean containsKey = kv.containsKey(key);
        LogUtil.i(TAG, "mmkv containsInternal :" + containsKey);
        return containsKey;
    }

    /**
     * 删除全部的数据
     *
     * @param mmapID
     */
    public void clearAllInternal(String mmapID, int mode) {
        MMKV kv = MMKV.mmkvWithID(mmapID, mode);
        kv.clearAll();
        LogUtil.i(TAG, "mmkv " + mmapID + " clearAll");
    }

    /**
     * 删除指定key的数据
     */
    private void clearAllInternal() {
        MMKV kv = MMKV.defaultMMKV();
        kv.clearAll();
        LogUtil.i(TAG, "mmkv clearAll");
    }

    @Override
    public void put(String key, Object value) {
        setInternal(key, value);
    }

    @Override
    public <T> T get(String key, T defValue) {
        return getInternal(key, defValue);
    }

    @Override
    public void remove(String key) {
        removeInternal(key);
    }

    @Override
    public boolean contains(String key) {
        return containsInternal(key);
    }

    @Override
    public void clear() {
        clearAllInternal();
    }


    /**------------------------------------ sp兼容-------------------------------------**/
    /**
     * 存储数据
     *
     * @param localDataType
     * @param key
     * @param value
     */
    public void setInternal(LocalDataType localDataType, String key, Object value) {
        setInternal(localDataType.getKey(), localDataType.getMode(), key, value);
    }

    /**
     * 获取数据
     *
     * @param localDataType
     * @param key
     * @param defValue
     * @param <T>
     * @return
     */
    public <T> T getInternal(LocalDataType localDataType, String key, T defValue) {
        return (T) getInternal(localDataType.getKey(), localDataType.getMode(), key, defValue);
    }

    /**
     * 删除指定key的数据
     *
     * @param localDataType
     * @param key
     */
    public void removeInternal(LocalDataType localDataType, String key) {
        MMKV kv = MMKV.mmkvWithID(localDataType.getKey(), localDataType.getMode());
        kv.removeValueForKey(key);
        LogUtil.i(TAG, "mmkv " + localDataType.getKey() + " remove :" + key);
    }

    /**
     * 删除指定key的数据
     *
     * @param localDataType
     */
    public void clearAllInternal(LocalDataType localDataType) {
        MMKV kv = MMKV.mmkvWithID(localDataType.getKey(), localDataType.getMode());
        kv.clearAll();
        LogUtil.i(TAG, "mmkv " + localDataType.getKey() + " clearAll");
    }


    /**
     * 老数据迁移
     *
     * @param context
     */
    public void importSharedPreferencesData(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //遍历枚举类迁移全部老数据至mmkv
                for (LocalDataType sharedType : LocalDataType.values()) {
                    try {
                        SharedPreferences oldSP1 = getSharedPreferences(context, sharedType);
                        if (oldSP1 != null && oldSP1.getAll() != null && oldSP1.getAll().size() > 0) {//有数据迁移 没数据跳过不影响性能
                            MMKV kv = MMKV.mmkvWithID(sharedType.getKey());
                            kv.importFromSharedPreferences(oldSP1);
                            oldSP1.edit().clear().commit();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 获取mmkv实例
     *
     * @param localDataType
     * @return
     */
    public MMKV getSharedPreferences(LocalDataType localDataType) {
        return MMKV.mmkvWithID(localDataType.getKey(), localDataType.getMode());
    }

    /**
     * 获取mmkv editor实例
     *
     * @param localDataType
     * @return
     */
    public SharedPreferences.Editor getEditor(LocalDataType localDataType) {
        return MMKV.mmkvWithID(localDataType.getKey(), localDataType.getMode()).edit();
    }

    /**
     * 根据内容取得sp实例
     *
     * @param localDataType
     * @return
     */
    private SharedPreferences getSharedPreferences(Context context, LocalDataType localDataType) {
        return context.getSharedPreferences(localDataType.getKey(),
                localDataType.getMode());
    }
}
