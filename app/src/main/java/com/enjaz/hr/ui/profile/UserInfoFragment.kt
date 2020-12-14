package com.enjaz.hr.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentUserInfoBinding
import com.enjaz.hr.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment :
    BaseFragment<FragmentUserInfoBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener {

    private val profileViewModel: ProfileViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_user_info
    }

    override fun getViewModel(): ProfileViewModel {
        return profileViewModel
    }

    override fun getNavigator(): IProfileInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEllipsize()

        getViewModel().getProfileInfo()


    }

    private fun setEllipsize(){
        getViewDataBinding().tvAddress.isSelected=true
        getViewDataBinding().tvAge.isSelected=true
        getViewDataBinding().tvBirthDate.isSelected=true
        getViewDataBinding().tvDatOfJoin.isSelected=true
        getViewDataBinding().tvDepartment.isSelected=true
        getViewDataBinding().tvEmail.isSelected=true
        getViewDataBinding().tvEmployeeId.isSelected=true
        getViewDataBinding().tvEmployeeStatus.isSelected=true
        getViewDataBinding().tvExperience.isSelected=true
        getViewDataBinding().tvMaritalStatus.isSelected=true
        getViewDataBinding().tvMobile.isSelected=true
        getViewDataBinding().tvReportTo.isSelected=true
        getViewDataBinding().tvRole.isSelected=true
        getViewDataBinding().tvWorkPhone.isSelected=true
    }
    override fun onPersonalDetailsClick() {
    }


    override fun onBalanceClick() {
        TODO("Not yet implemented")
    }

    override fun onSalaryDetailsClick() {
        TODO("Not yet implemented")
    }

    override fun onSettingsClick() {
        TODO("Not yet implemented")
    }

    override fun detailsAvailable() {
        TODO("Not yet implemented")
    }

    override fun noDetails() {
        TODO("Not yet implemented")
    }

    override fun hideLeaveCreditView() {
        TODO("Not yet implemented")
    }

    override fun showLeaveCreditView() {
        TODO("Not yet implemented")
    }


}


