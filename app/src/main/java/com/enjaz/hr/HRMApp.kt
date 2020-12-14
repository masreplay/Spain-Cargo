package com.enjaz.hr

import android.app.Application
import android.content.Context
import com.enjaz.hr.util.PrefsManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class HRMApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, Locale.getDefault())
        PrefsManager.init(this)
        Fresco.initialize(this)
    }

    init {
        instance = this
    }



    companion object {
        var instance: HRMApp? = null

        fun applicationContext(): Context {

            return instance!!.applicationContext
        }
    }
}
