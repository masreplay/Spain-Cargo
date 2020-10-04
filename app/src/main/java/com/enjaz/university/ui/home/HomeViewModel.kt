package com.enjaz.university.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IHomeInteractionListener>(
    dataManager
) {

    fun mylog() {
        Log.i("abdalla19977", "abdalla19977")
    }
}