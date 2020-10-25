package com.enjaz.hr.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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
        var isShow = true
        var scrollRange = -1
        getViewDataBinding().appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset <= 56) {
                getViewDataBinding().image.hide()
                isShow = true
            } else if (isShow) {

                getViewDataBinding().image.show()


                isShow = false
            }
        })
    }






}

interface IProfileInteractionListener : BaseNavigator {

}
