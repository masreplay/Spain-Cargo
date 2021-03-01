package com.spain_cargo.cargo.ui.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.util.show
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.databinding.FragmentProfileBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.ui.login.LoginActivity
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.copyToClipBoard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener {

    private val addItemViewModel: ProfileViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_profile
    override fun getViewModel() = addItemViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getProfile()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().apply {
            btnLogout.setOnClickListener {
                logout()
            }
            btnUpdateProfile.setOnClickListener {
                findNavController().navigate(R.id.profile_update_fragment)
            }
        }
    }

    override fun copy(key: String) {
        this.copyToClipBoard(key)
    }

    private fun logout() {
        AlertDialog.Builder(requireContext()).apply {
            setCancelable(true)
            setMessage(getString(R.string.msg_logout))
            setPositiveButton(getString(R.string.option_yes)) { _, _ ->
                getViewModel().logout()
                getViewDataBinding().btnLogout.show()
                PrefsManager.instance?.clearPreferences()
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
            }
            setNegativeButton(getString(R.string.option_no)) { _, _ -> }
        }.create().show()
    }

}

interface IProfileInteractionListener : BaseNavigator{
    fun copy(key:String)
}