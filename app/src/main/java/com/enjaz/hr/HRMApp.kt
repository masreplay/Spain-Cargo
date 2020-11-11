package com.enjaz.hr

import android.app.Application
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class HRMApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, Locale.getDefault())
    }
}
