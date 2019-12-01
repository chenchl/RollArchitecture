package cn.chenchl.rollarch.commonlib.image;


import cn.chenchl.rollarch.commonlib.image.glide.GlideLoaderImpl;

/**
 * created by ccl on 2019/2/20
 **/
public class ImageLoaderUtil {

    private static class Holder {
        private static IImageLoader INSTANCE = new ImageLoaderUtil().loader;
    }

    public static IImageLoader getInstance() {
        return Holder.INSTANCE;
    }

    private IImageLoader loader;

    private ImageLoaderUtil() {
        loader = new GlideLoaderImpl();
    }
}
