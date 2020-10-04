package com.enjaz.university

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UniversityApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
