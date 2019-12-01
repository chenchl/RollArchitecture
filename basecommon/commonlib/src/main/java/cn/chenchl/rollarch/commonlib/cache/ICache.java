package cn.chenchl.rollarch.commonlib.cache;

import android.content.Context;

/**
 * created by hasee on 2019/12/1
 **/
public interface ICache {

    void initCache(Context context);

    void put(String key, Object value);

    <T> T get(String key, T defValue);

    void remove(String key);

    boolean contains(String key);

    void clear();
}
