package com.enjaz.university.ui.schedual

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.BaseResponse
import com.enjaz.university.data.model.schedule.ScheduleResponse
import com.enjaz.university.ui.base.BaseViewModel

class ScheduleViewModel(dataManager: AppDataManager) : BaseViewModel<IScheduleInteractionListener>(
    dataManager
) {


    var getScheduleResponse: MutableLiveData<BaseResource<BaseResponse<ScheduleResponse>>> =
        MutableLiveData()


    fun getSchedulle(filter: String) {
        dispose(dataManager.getSchedule(filter), ::onGetScheduleSuccess, { e ->
            run {

                Log.d("ausamah", e.localizedMessage)


            }
        })
    }


    private fun onGetScheduleSuccess(result: BaseResource<BaseResponse<ScheduleResponse>>) {
        Log.d("ausamah", " success")

        getScheduleResponse.value = result

    }


}

