package com.enjaz.hr.ui.base

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import kotlin.reflect.KFunction1

abstract class BaseViewModel<N : BaseNavigator>(
    val dataManager: AppDataManager
) : ViewModel() {
    val isLoading = ObservableBoolean(false)
    var refreshListener: MutableLiveData<View.OnClickListener> = MutableLiveData()

    // Architecture elements
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var refNavigator: WeakReference<N>? = null

    var navigator: N
        get() = refNavigator!!.get()!!
        set(navigator) {
            this.refNavigator = WeakReference(navigator)
        }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun disposeOnExit(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun <T> dispose(
        request: Single<BaseResource<T>>,
        consumerObject: MutableLiveData<BaseResource<T>>
    ) {
        consumerObject.postValue(BaseResource.loading(null))
        disposeOnExit(
            request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    consumerObject.postValue(it)
                }, {
                    Log.e(this::class.java.name, "", it)
                    consumerObject.postValue(BaseResource.error(it.message!!, null))
                })
        )
    }

    protected fun <T> dispose(
        request: Single<T>,
        onSuccess: KFunction1<T, Unit>,
        onError: (Throwable) -> Unit
    ) {

        disposeOnExit(
            request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .let {
                    if (onError != null) {
                        it.subscribe(Consumer(onSuccess), Consumer(onError))
                    } else {
                        it.subscribe(Consumer(onSuccess))
                    }
                }
        )
    }

}


interface BaseNavigator
