package cn.chenchl.rollarch.commonlib.image.glide.extension;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;

import cn.chenchl.rollarch.commonlib.image.glide.GlideCircleTransform;

/**
 * created by ccl on 2019/2/19
 **/
@GlideExtension
public class BaseAppGlideExtension {

    private static final int MINI_THUMB_SIZE = 100;

    private BaseAppGlideExtension() {
    }

    @GlideOption
    public static BaseRequestOptions<?> diskCacheAll(BaseRequestOptions<?> options) {
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        return options;
    }

    @GlideOption
    public static BaseRequestOptions<?> diskCacheNone(BaseRequestOptions<?> options) {
        options.diskCacheStrategy(DiskCacheStrategy.NONE);
        return options;
    }

    @GlideOption
    public static BaseRequestOptions<?> diskCacheAuto(BaseRequestOptions<?> options) {
        options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        return options;
    }

    @GlideOption
    public static BaseRequestOptions<?> diskCacheResource(BaseRequestOptions<?> options) {
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }

    @GlideType(String.class)
    public static RequestBuilder<String> asCircle(RequestBuilder<String> requestBuilder) {
        requestBuilder.transform(new GlideCircleTransform()).centerCrop();
        return requestBuilder;
    }
}
