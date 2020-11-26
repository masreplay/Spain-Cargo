package com.enjaz.hr.ui.notifications

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.Data
import com.enjaz.hr.data.model.Notification
import com.enjaz.hr.ui.base.BaseViewModel

class NotificationsViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<INotificationsInteractionListener>(
    dataManager
) {

    var notificationsResponse: MutableLiveData<MutableList<Notification>> =
        MutableLiveData()


    fun getdata(page: Int) {

        notificationsResponse.value = mutableListOf(
            Notification(
                "Balance addition",
                "<b>1,000$</b> added to your balance , check your card balance",
                1
            ),
            Notification(
                "Balance deduction",
                "<b>200$</b> deducted from your balance , check your card balance",
                2
            ),
            Notification(
                "Balance deduction",
                "<b>100$</b> deducted from your balance , check your card balance",
                2
            ),
            Notification(
                "Balance addition",
                "<b>1,000$</b> added to your balance , check your card balance",
                1
            ),
            Notification(
                "Balance addition",
                "<b>1,000$</b> added to your balance , check your card balance",
                1
            )
        )

    }

    fun appenddata() {
        notificationsResponse.value?.add(Notification(
            "Balance addition",
            "<b>1,000$</b> added to your balance , check your card balance",
            1
        ))
        notificationsResponse.value?.add( Notification(
            "Balance deduction",
            "<b>200$</b> deducted from your balance , check your card balance",
            2
        ))
        notificationsResponse.value?.add(Notification(
            "Balance addition",
            "<b>1,000$</b> added to your balance , check your card balance",
            1
        ))

        notificationsResponse.value = notificationsResponse.value
    }

    private fun onGetNotificationsSuccess(result: BaseResource<Data>) {


    }

}