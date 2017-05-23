package com.pvj.xlibrary.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by pvj on 2016/12/15.
 * 在 app 清单文件中配置
 *  <meta-data android:name="com.pvj.xlibrary.glide.CustomGlideModule" android:value="GlideModule"/>
 */

public class CustomGlideModule  implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //        .setMemoryCache(MemoryCache memoryCache)
        //        .setBitmapPool(BitmapPool bitmapPool)
        //        .setDiskCache(DiskCache.Factory diskCacheFactory)
        //        .setDiskCacheService(ExecutorService service)
        //        .setResizeService(ExecutorService service)
        //        .setDecodeFormat(DecodeFormat decodeFormat)

        //  builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);


        int cacheSize = 10 << 20; // 内部磁盘缓存 或者外部磁盘缓存
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize)
                //new ExternalCacheDiskCacheFactory(context, cacheSize)
        );
    }


    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
