package com.enjaz.hr.ui.sentRequest

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentSendRequsetBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendRequestFragment :
    BaseFragment<FragmentSendRequsetBinding, ISendRequestInteractionListener, SendRequestViewModel>(),
    ISendRequestInteractionListener {

    private val sedRequestViewModel: SendRequestViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_send_requset
    }

    override fun getViewModel(): SendRequestViewModel {
        return sedRequestViewModel
    }

    override fun getViewModelClass(): Class<SendRequestViewModel> {
        return SendRequestViewModel::class.java
    }

    override fun getNavigator(): ISendRequestInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getViewDataBinding().switchOption1.setOnCheckedChangeListener { compoundButton, bolean ->



        }


    }


}

interface ISendRequestInteractionListener : BaseNavigator {

}
