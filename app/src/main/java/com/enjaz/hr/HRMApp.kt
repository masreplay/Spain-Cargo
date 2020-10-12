package com.enjaz.hr

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@HiltAndroidApp
class HRMApp : Application() {

    override fun onCreate() {
        super.onCreate()


    }
}
