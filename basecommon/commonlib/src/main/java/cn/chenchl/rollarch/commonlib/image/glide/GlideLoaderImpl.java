package cn.chenchl.rollarch.commonlib.image.glide;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import java.io.File;

import cn.chenchl.rollarch.commonlib.image.IImageLoader;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * created by ccl on 2019/2/20
 **/
public class GlideLoaderImpl implements IImageLoader {

    private RequestManager getRequestManager(Context context) {
        if (context instanceof Activity) {
            return GlideUtils.with((Activity) context);
        }
        return GlideUtils.with(context);
    }

    private void load(Context context, Object model, ImageView target, Options options) {
        if (options == null) options = Options.defaultOptions();
        try {
            options.scaleType(target.getScaleType());
            RequestOptions requestOptions = wrapScaleType(options);

            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().
                    crossFade(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build());

            getRequestManager(context)
                    .load(model)
                    .apply(requestOptions)
                    .transition(drawableTransitionOptions)
                    .into(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置RequestOptions
     *
     * @param options
     * @return
     */
    private RequestOptions wrapScaleType(Options options) {
        RequestOptions request = new RequestOptions()
                .priority(Priority.HIGH);
        if (options != null) {
            switch (options.diskCacheStrategyMode) {
                case Options.CACHE_AUTO:
                    request.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                case Options.CACHE_ALL:
                    request.diskCacheStrategy(DiskCacheStrategy.ALL);
                case Options.CACHE_NONE:
                    request.diskCacheStrategy(DiskCacheStrategy.NONE);
                case Options.CACHE_RESOURCE:
                    request.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            }
            if (!options.isNeedAnim)
                request.dontAnimate();
            if (options.loadingResId != Options.RES_NONE) {
                request.placeholder(options.loadingResId);
            }
            if (options.loadErrorResId != Options.RES_NONE) {
                request.error(options.loadErrorResId);
            }
            switch (options.scaleType) {
                case MATRIX:
                case FIT_XY:
                case FIT_START:
                case FIT_END:
                case CENTER:
                case CENTER_INSIDE:
                    break;
                case FIT_CENTER:
                    request.fitCenter();
                    break;
                case CENTER_CROP:
                    request.centerCrop();
                    break;
            }
        } else {
            request.centerCrop();
        }
        return request;
    }

    @Override
    public void loadUrlDefault(Context context, Options options, String url, ImageView target) {
        load(context, url, target, options);
    }

    @Override
    public void loadUrlDefault(Context context, String url, ImageView target) {
        load(context, url, target, Options.defaultOptions());
    }

    @Override
    public void loadResource(Context context, ImageView target, int resId, Options options) {
        options.diskCacheStrategyMode = Options.CACHE_RESOURCE;
        load(context, resId, target, options);
    }

    @Override
    public void loadAssets(Context context, ImageView target, String assetName, Options options) {
        options.diskCacheStrategyMode = Options.CACHE_NONE;
        load(context, "file:///android_asset/" + assetName, target, options);
    }

    @Override
    public void loadFile(Context context, ImageView target, String filePath, Options options) {
        options.diskCacheStrategyMode = Options.CACHE_NONE;
        load(context, filePath, target, options);
    }

    @Override
    public void loadFile(Context context, ImageView target, File file, Options options) {
        options.diskCacheStrategyMode = Options.CACHE_NONE;
        load(context, file, target, options);
    }

    @Override
    public void loadCircle(Context context, Object url, ImageView target, Options options) {
        try {
            RequestOptions requestOptions = wrapScaleType(options);

            getRequestManager(target.getContext())
                    .load(url)
                    .apply(requestOptions)
                    .transform(new GlideCircleTransform())
                    .transition(withCrossFade())
                    .into(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadCorner(Context context, Object url, ImageView target, int radius, Options options) {
        try {
            options.scaleType(target.getScaleType());
            RequestOptions requestOptions = wrapScaleType(options);

            //设置图片圆角角度
            MultiTransformation multiTransformation = new MultiTransformation(new CenterCrop(), new RoundedCorners(radius));
            requestOptions.transform(multiTransformation);

            getRequestManager(target.getContext())
                    .load(url)
                    .apply(requestOptions)
                    .transition(withCrossFade())
                    .into(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearMemoryCache(Context context) {
        GlideUtils.get(context).clearMemory();
    }

    @Override
    public void trimMemory(Context context, int level) {
        GlideUtils.get(context).trimMemory(level);
    }

    @Override
    public void clearDiskCache(Context context) {
        GlideUtils.get(context).clearDiskCache();
    }

    @Override
    public void resume(Context context) {
        getRequestManager(context).resumeRequests();
    }

    @Override
    public void pause(Context context) {
        getRequestManager(context).pauseRequests();
    }
}
