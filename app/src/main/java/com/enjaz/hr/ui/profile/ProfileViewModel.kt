package com.enjaz.hr.ui.profile

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.enjaz.hr.data.AppDataManager
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.balance.BalanceResponse
import com.enjaz.hr.data.model.profile.UserInfo
import com.enjaz.hr.data.model.salary.SalaryDetailsResponse
import com.enjaz.hr.ui.base.BaseViewModel
import com.enjaz.hr.util.PrefsManager

class ProfileViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IProfileInteractionListener>(
    dataManager
) {

    var balanceResponse: MutableLiveData<BaseResource<BalanceResponse>> = MutableLiveData()
    var salaryDetailsResponse: MutableLiveData<BaseResource<SalaryDetailsResponse>> =
        MutableLiveData()

    var userInfoResponse: MutableLiveData<BaseResource<UserInfo>> = MutableLiveData()


    fun getSalaryDetails() {
        salaryDetailsResponse.value = BaseResource.loading(salaryDetailsResponse.value?.data)
        dispose(
            dataManager.getSalaryDetails(),
            ::onGetSalaryDetailsSuccess,
            { e ->
                //error handling
                e.message?.let {
                    salaryDetailsResponse.postValue(BaseResource.error(it, null))
                    Log.d("error", it)
                }


            })
        refreshListener.postValue(View.OnClickListener { getSalaryDetails() })


    }

    fun getProfileInfo() {
        userInfoResponse.value = BaseResource.loading(userInfoResponse.value?.data)
        dispose(
            dataManager.getProfileInfo(),
            ::onGetProfileInfoSuccess,
            { e ->
                //error handling

                userInfoResponse.postValue(BaseResource.success(PrefsManager.instance?.getProfile()))

            })
        refreshListener.postValue(View.OnClickListener { getProfileInfo() })

    }


    private fun onGetProfileInfoSuccess(result: BaseResource<UserInfo>) {
        userInfoResponse.postValue(result)
        result.data?.let { PrefsManager.instance?.saveProfile(it) }
        Log.i("ksajdflkdjsh", userInfoResponse.value?.data.toString())
    }

    fun getLeaveBalance() {


        balanceResponse.value = BaseResource.loading(balanceResponse.value?.data)



        dispose(
            dataManager.getLeaveBalance(),
            ::onGetBalanceSuccess,
            { e ->
                //error handling
                e.message?.let {
                    balanceResponse.postValue(BaseResource.error(it, null))
                    Log.d("ksajdflkdjshsdf", it)
                }


            })
    }

    private fun onGetSalaryDetailsSuccess(result: BaseResource<SalaryDetailsResponse>) {


        if (result.message != null) {


//            navigator.showSnack(result.message, "#ED213A", R.drawable.ic_round_close_24)


        } else result.data?.let {


            salaryDetailsResponse.postValue(result)

            if (it.items.isEmpty()) {
                navigator.noDetails()
            } else {
                navigator.detailsAvailable()

            }

        }
    }


    private fun onGetBalanceSuccess(result: BaseResource<BalanceResponse>) {

        balanceResponse.postValue(result)

        if (result.message != null) {


            navigator.hideLeaveCreditView()


        } else result.data?.let {


            if (it.balances.isEmpty()) {
                navigator.hideLeaveCreditView()
            } else {
                navigator.showLeaveCreditView()

            }

        }

    }

}