package com.enjaz.hr.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.util.hide
import com.afollestad.vvalidator.util.show
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentProfileBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener {

    private val profileViewModel: ProfileViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun getViewModel(): ProfileViewModel {
        return profileViewModel
    }

    override fun getViewModelClass(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun getNavigator(): IProfileInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().mylog()



    }

    override fun onPersonalDetailsClick() {
        findNavController().navigate(R.id.userInfoFragment)
    }

    override fun onSalaryDetailsClick() {
        findNavController().navigate(R.id.salaryDetailsFragment)
    }


}

interface IProfileInteractionListener : BaseNavigator {
    fun onPersonalDetailsClick()

    fun onSalaryDetailsClick()
}
