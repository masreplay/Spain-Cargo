package com.enjaz.hr.ui.attendance


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.DateItem
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.ui.base.BaseViewModel

class AttendanceViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IAttendanceInteractionListener>(
    dataManager
) {

    var dates: MutableLiveData<MutableList<DateItem>> =
        MutableLiveData()

    var strings: MutableLiveData<MutableList<String>> =
        MutableLiveData()


    fun getdata() {
        strings.value = mutableListOf(
            "abd",
            "hussein",
            "husseain",
            "husasein",
            "husseain",
            "hussaein",
            "hussaein"
        )
        dates.value = mutableListOf(
            DateItem("Jan"),
            DateItem("Feb"),
            DateItem("Mar"),
            DateItem("Apr"),
            DateItem("May"),
            DateItem("Jun"),
            DateItem("Jul"),
            DateItem("Aug"),
            DateItem("Sept"),
            DateItem("Oct"),
            DateItem("Nov", true),
            DateItem("Dec")
        )

    }


    private fun onLoginSuccess(result: BaseResource<TokenResult>) {

    }

}