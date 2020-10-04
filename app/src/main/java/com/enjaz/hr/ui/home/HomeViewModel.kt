package com.enjaz.hr.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IHomeInteractionListener>(
    dataManager
) {


    fun mylog() {
        dispose(
            dataManager.login("email.value!!.trim()", "pass.value!!.trim()"),
            ::onLoginSuccess,
            { e ->
                //error handling
                run {

                    Log.d("ausamah",e.message)
                }

            })
    }

    private fun onLoginSuccess(result: BaseResource<BaseResponse<TokenResult>>) {
        Log.d("ausamah","sss")

    }

}