package com.enjaz.university.ui.grades

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.BaseResponse
import com.enjaz.university.data.model.grades.GradesResponse
import com.enjaz.university.ui.base.BaseViewModel

class GradesViewModel(
    dataManager: AppDataManager
) : BaseViewModel<IGradesInteractionListener>(dataManager) {


    var getGradesResponse: MutableLiveData<BaseResource<BaseResponse<MutableList<GradesResponse>>>> =
        MutableLiveData()


    fun getGrades() {
        dispose(dataManager.getGrades(), ::onGetGradesSuccess, { e ->
            run {

                Log.d("error", e.localizedMessage)


            }
        })
    }


    private fun onGetGradesSuccess(result: BaseResource<BaseResponse<MutableList<GradesResponse>>>) {
        Log.d("ausamah", " success")

        getGradesResponse.value = result


    }


}