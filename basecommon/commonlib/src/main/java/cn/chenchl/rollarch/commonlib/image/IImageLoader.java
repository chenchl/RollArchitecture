package cn.chenchl.rollarch.commonlib.image;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

import cn.chenchl.rollarch.commonlib.BaseConfig;

/**
 * created by ccl on 2019/2/20
 **/
public interface IImageLoader {

    void loadUrlDefault(Context context, Options options, String url, ImageView target);

    void loadUrlDefault(Context context, String url, ImageView target);

    void loadResource(Context context, ImageView target, int resId, Options options);

    void loadAssets(Context context, ImageView target, String assetName, Options options);

    void loadFile(Context context, ImageView target, String filePath, Options options);

    void loadFile(Context context, ImageView target, File file, Options options);

    void loadCircle(Context context, Object url, ImageView target, Options options);

    void loadCorner(Context context, Object url, ImageView target, int radius, Options options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    void trimMemory(Context context, int level);

    void resume(Context context);

    void pause(Context context);

    class Options {

        public boolean isNeedAnim;
        public int loadingResId = RES_NONE;        //加载中的资源id
        public int loadErrorResId = RES_NONE;      //加载失败的资源id
        public ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
        public int diskCacheStrategyMode = 0;

        public static final int CACHE_AUTO = 0;
        public static final int CACHE_NONE = 1;
        public static final int CACHE_RESOURCE = 2;
        public static final int CACHE_ALL = 3;

        public static final int RES_NONE = -1;

        public static Options defaultOptions() {
            return new Options(BaseConfig.IL_LOADING_RES, BaseConfig.IL_ERROR_RES, true, 0);
        }
        public static Options defaultOptionsNoRes() {
            return new Options(RES_NONE, RES_NONE, true, 0);
        }

        public Options(int loadingResId, int loadErrorResId, boolean isNeedAnim, int diskCacheStrategyMode) {
            this.diskCacheStrategyMode = diskCacheStrategyMode;
            this.isNeedAnim = isNeedAnim;
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }

        public Options scaleType(ImageView.ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }
    }

}
