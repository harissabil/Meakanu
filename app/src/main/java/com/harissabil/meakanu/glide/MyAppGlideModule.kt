package com.harissabil.meakanu.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        val memoryCacheSizeBytes = 1024 * 1024 * 30 // 30 MB
        builder.setMemoryCache(
            LruResourceCache(memoryCacheSizeBytes.toLong())
        )
        builder.setDiskCache(
            ExternalPreferredCacheDiskCacheFactory(context, "glide-cache",
                memoryCacheSizeBytes.toLong()
            )
        )
    }
}