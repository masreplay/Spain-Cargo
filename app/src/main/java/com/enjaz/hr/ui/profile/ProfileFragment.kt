package com.enjaz.hr.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentProfileBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener {

    private val profileViewModel: ProfileViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun getViewModel(): ProfileViewModel {
        return profileViewModel
    }

    override fun getNavigator(): IProfileInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getProfileInfo()

    }

    override fun onPersonalDetailsClick() {
        findNavController().navigate(R.id.userInfoFragment)
    }

    override fun onBalanceClick() {
        findNavController().navigate(R.id.balanceFragment)
    }


    override fun onSalaryDetailsClick() {
        findNavController().navigate(R.id.salaryDetailsFragment)
    }

    override fun onSettingsClick() {
        findNavController().navigate(R.id.settingsFragment)
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

interface IProfileInteractionListener : BaseNavigator {
    fun onPersonalDetailsClick()

    fun onBalanceClick()
    fun onSalaryDetailsClick()
    fun onSettingsClick()
    fun detailsAvailable()
    fun noDetails()
    fun hideLeaveCreditView()
    fun showLeaveCreditView()
}
