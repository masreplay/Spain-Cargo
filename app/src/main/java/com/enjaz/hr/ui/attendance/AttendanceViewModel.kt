package com.enjaz.hr.ui.attendance


import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.R
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.attendance.AttendanceResponse
import com.enjaz.hr.ui.base.BaseViewModel

class AttendanceViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IAttendanceInteractionListener>(
    dataManager
) {

    var attendanceResponse: MutableLiveData<BaseResource<AttendanceResponse>> = MutableLiveData()


    fun getAttendanceData(month: Int, year: Int) {
        attendanceResponse.value = BaseResource.loading(attendanceResponse.value?.data)
        dispose(
            dataManager.getAttendance(month, year),
            ::onGetAttendanceSuccess,
            { e ->
                //error handling
                e.message?.let {
                    attendanceResponse.postValue(BaseResource.error(it, null))
                    Log.d("error", it)
                }
            })
        refreshListener.postValue(View.OnClickListener { getAttendanceData(month, year) })
    }

    fun getEmployeeAttendanceResponse(month: Int, year: Int, employeeId: Int) {
        attendanceResponse.value = BaseResource.loading(attendanceResponse.value?.data)
        dispose(
            dataManager.getEmployeeAttendanceResponse(month, year, employeeId),
            ::onGetAttendanceSuccess,
            { e ->
                //error handling
                e.message?.let {
                    attendanceResponse.postValue(BaseResource.error(it, null))
                    Log.d("error", it)
                }
            })
        refreshListener.postValue(View.OnClickListener { getEmployeeAttendanceResponse(month, year, employeeId) })
    }


    private fun onGetAttendanceSuccess(result: BaseResource<AttendanceResponse>) {


        result.message?.let {
            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)
        }

        result.data?.let {
            attendanceResponse.postValue(result)
            if (it.days.isEmpty()) {
                navigator.noAttendance()
            } else {
                navigator.attendanceAvailable()
            }
        }

    }

}