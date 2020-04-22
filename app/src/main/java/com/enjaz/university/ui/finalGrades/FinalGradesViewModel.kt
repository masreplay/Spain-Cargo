package com.enjaz.university.ui.finalGrades

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.BaseResponse
import com.enjaz.university.data.model.grades.GradesResponse
import com.enjaz.university.ui.base.BaseViewModel

class FinalGradesViewModel(
    dataManager: AppDataManager
) : BaseViewModel<IFinalGradesInteractionListener>(dataManager) {


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