package com.enjaz.university.util

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//updates a livedata object, especially a list that isn't observed when adding elements

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun getLengthString(length: String?): String? {
    length?.apply {
        val numberValue = this.toIntOrNull()
        numberValue?.apply {
            val inch = 0.3937 * this
            val feet = 0.0328 * this
            return "$length cm / $feet ft, $inch in"
        }
    }
    return "0 cm / 0 ft, 0 in"
}

fun bundleToMap(extras: Bundle?): Map<String, Any> {
    val map = HashMap<String, Any>()

    extras?.let {
        val ks = extras.keySet()
        val iterator = ks.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            map[key] = extras.get(key)!!
        }
    }
    return map
}

@SuppressLint("CheckResult")
fun callInBackground(runnable: Runnable) {
    Observable.create { o: ObservableEmitter<Any?> ->
        runnable.run()
        o.onNext(true)
    }.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe { }
}




