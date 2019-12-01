package cn.chenchl.rollarch.commonlib.cache;

import android.content.Context;

/**
 * created by hasee on 2019/12/1
 **/
public class LocalDataProxy implements ICache {

    private LocalDataProxy() {

    }

    private static class Holder {
        private static LocalDataProxy INSTANCE = new LocalDataProxy();
    }

    public static LocalDataProxy getInstance() {
        return Holder.INSTANCE;
    }

    private ICache cache;

    public void initCache(ICache cache, Context context) {
        this.cache = cache;
        this.cache.initCache(context);
    }

    /**
     * 初始化本地缓存 默认MMKV
     *
     * @param context
     */
    @Override
    public void initCache(Context context) {
        this.cache = MMKVUtil.getInstance();
        this.cache.initCache(context);
    }

    @Override
    public void put(String key, Object value) {
        if (this.cache == null) {
            throw new NullPointerException("cache is Null,call initcache plz");
        }
        cache.put(key, value);
    }

    @Override
    public <T> T get(String key, T defValue) {
        if (this.cache == null) {
            throw new NullPointerException("cache is Null,call initcache plz");
        }
        return cache.get(key, defValue);
    }

    @Override
    public void remove(String key) {
        if (this.cache == null) {
            throw new NullPointerException("cache is Null,call initcache plz");
        }
        cache.remove(key);
    }

    @Override
    public boolean contains(String key) {
        if (this.cache == null) {
            throw new NullPointerException("cache is Null,call initcache plz");
        }
        return cache.contains(key);
    }

    @Override
    public void clear() {
        if (this.cache == null) {
            throw new NullPointerException("cache is Null,call initcache plz");
        }
        cache.clear();
    }
}
