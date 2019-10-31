package cn.chenchl.rollarch.commonlib.cache;

import android.text.TextUtils;
import android.util.LruCache;


/**
 * Created by ccl on 2019/02/20.
 * 内存缓存类 lrucache实现
 */
public class MemoryCache {

    private LruCache<String, Object> cache;

    private MemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        cache = new LruCache<String, Object>(cacheSize);

    }

    private static class Holder {
        private static MemoryCache INSTANCE = new MemoryCache();
    }

    public static MemoryCache getInstance() {
        return MemoryCache.Holder.INSTANCE;
    }


    public synchronized void put(String key, Object value) {
        if (TextUtils.isEmpty(key)) return;

        if (cache.get(key) != null) {
            cache.remove(key);
        }
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public synchronized <T> T get(String key, Class<T> clazz) {
        try {
            return (T) cache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(String key) {
        if (cache.get(key) != null) {
            cache.remove(key);
        }
    }

    public boolean contains(String key) {
        return cache.get(key) != null;
    }

    public void clear() {
        cache.evictAll();
    }
}
