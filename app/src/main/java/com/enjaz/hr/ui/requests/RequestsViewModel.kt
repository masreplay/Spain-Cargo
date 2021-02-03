package com.enjaz.hr.ui.requests

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.R
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.getLeaveRequests.LeavesResponse
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.util.Constants.ITEMS_PER_PAGE

class RequestsViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IRequestsInteractionListener>(
    dataManager
) {

    var leaveRequestResponse: MutableLiveData<BaseResource<LeavesResponse>> = MutableLiveData()
    var oldLeaveRequestResponse: MutableLiveData<BaseResource<LeavesResponse>> = MutableLiveData()
    var cancelMyRequestResponse: MutableLiveData<BaseResource<String>> = MutableLiveData()

    fun getLeaveRequests(
        isManager: Boolean = false,
        IsHistory: Boolean = false,
        skipCount: Int,
        maxResult: Int = ITEMS_PER_PAGE
    ) {
        leaveRequestResponse.value = BaseResource.loading(leaveRequestResponse.value?.data)
        dispose(
            dataManager.getLeaveRequests(isManager, IsHistory, skipCount, maxResult),
            ::onGetLeaveRequestsSuccess,
            { e ->
                //error handling
                e.message?.let {
                    leaveRequestResponse.postValue(BaseResource.error(it, null))
                }
            })

    }

    fun getOldLeaveRequests(
        isManager: Boolean = true,
        IsHistory: Boolean = true,
        skipCount: Int,
        maxResult: Int = ITEMS_PER_PAGE
    ) {
        oldLeaveRequestResponse.value = BaseResource.loading(oldLeaveRequestResponse.value?.data)
        dispose(
            dataManager.getLeaveRequests(isManager, IsHistory, skipCount, maxResult),
            ::onGetOldLeaveRequestsSuccess,
            { e ->
                //error handling
                e.message?.let {
                    oldLeaveRequestResponse.postValue(BaseResource.error(it, null))
                    Log.d("error", it)
                }
            })

    }

    fun changeRequestStatus(workCorrelationId: String, newStatus: Int, isManager: Boolean) {

        cancelMyRequestResponse.value = BaseResource.loading(cancelMyRequestResponse.value?.data)

        dispose(
            dataManager.cancelMyRequest(workCorrelationId, newStatus, isManager),
            ::onCancelRequestsSuccess,
            { e ->
                //error handling
                e.message?.let {
                    cancelMyRequestResponse.postValue(BaseResource.error(it, null))
                }


            })

    }

    private fun onGetLeaveRequestsSuccess(result: BaseResource<LeavesResponse>) {

        result.message?.let {

            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)
            leaveRequestResponse.postValue(result)
        }


        result.data?.let {
            leaveRequestResponse.postValue(result)
            navigator.onSuccess(result.data)
        }


    }

    private fun onGetOldLeaveRequestsSuccess(result: BaseResource<LeavesResponse>) {

        result.message?.let {
            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)
            oldLeaveRequestResponse.postValue(result)
        }


        result.data?.let {
            oldLeaveRequestResponse.postValue(result)
            navigator.onHistorySuccess(result.data)
        }


    }


    private fun onCancelRequestsSuccess(result: BaseResource<String>) {

        if (result.message != null) {
            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)
        } else {
            navigator.onChangeRequestSuccess()
            cancelMyRequestResponse.postValue(result)
        }

    }

}