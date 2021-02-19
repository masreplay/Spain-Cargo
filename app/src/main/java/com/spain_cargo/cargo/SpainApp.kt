package com.spain_cargo.cargo

import android.app.Application
import android.content.Context
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.common.util.ByteConstants
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.spain_cargo.cargo.util.PrefsManager
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient


@HiltAndroidApp
class SpainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefsManager.init()
        val imagePipelineConfig = OkHttpImagePipelineConfigFactory
            .newBuilder(this, OkHttpClient.Builder().build())
            .setMainDiskCacheConfig(
                DiskCacheConfig.newBuilder(this)
                    .setMaxCacheSize(100L * ByteConstants.MB)
                    .setMaxCacheSizeOnLowDiskSpace(10L * ByteConstants.MB)
                    .setMaxCacheSizeOnVeryLowDiskSpace(5L * ByteConstants.MB)
                    .build()
            )
            .build()

        Fresco.initialize(this,imagePipelineConfig)
    }

    init {
        instance = this
    }



    companion object {
        var instance: SpainApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}
