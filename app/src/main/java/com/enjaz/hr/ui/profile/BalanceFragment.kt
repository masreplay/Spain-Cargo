package com.enjaz.hr.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentBalanceBinding
import com.enjaz.hr.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BalanceFragment :
    BaseFragment<FragmentBalanceBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener {

    private val profileViewModel: ProfileViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_balance

    }

    override fun getViewModel(): ProfileViewModel {
        return profileViewModel
    }


    override fun getNavigator(): IProfileInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onPersonalDetailsClick() {
        TODO("Not yet implemented")
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


}

