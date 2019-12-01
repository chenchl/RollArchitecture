package cn.chenchl.rollarch.commonlib.image.glide;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

import androidx.annotation.NonNull;
import cn.chenchl.rollarch.commonlib.BaseConfig;


/**
 * created by ccl on 2019/2/19
 * glide baseGlideModule
 **/
@GlideModule(glideName = "GlideUtils")
public class BaseAppGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        //内存缓存大小
        int memoryCacheSizeBytes = BaseConfig.GLIDE_MEMORYCACHE_MAXSIZE;
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        //bitmap池大小
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setBitmapPoolScreens(3)
                .build();
        builder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()));
    }
}
