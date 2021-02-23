package com.spain_cargo.cargo.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.databinding.FragmentProfileBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener {

    private val profileViewModel: ProfileViewModel by viewModels()


    override fun getLayoutId() = R.layout.fragment_profile

    override fun getViewModel() = profileViewModel

    override fun getNavigator() = this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().getUsers()
    }
}

interface IProfileInteractionListener : BaseNavigator {

}
