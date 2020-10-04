package com.enjaz.university.ui.home

import android.util.Log
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.ui.base.BaseViewModel

class HomeViewModel(dataManager: AppDataManager) : BaseViewModel<IHomeInteractionListener>(
    dataManager
) {

    fun mylog(){
        Log.i("abdalla19977","abdalla19977")
    }
}