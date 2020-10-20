package com.enjaz.hr.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.framgnet_requests.*

@AndroidEntryPoint
class RequestsFragment :
    BaseFragment<FramgnetRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()
    lateinit var fragment_sent: Fragment
    lateinit var fragment_received: Fragment
    override fun getLayoutId(): Int {
        return R.layout.framgnet_requests
    }

    override fun getViewModel(): RequestsViewModel {
        return requestsViewModel
    }

    override fun getViewModelClass(): Class<RequestsViewModel> {
        return RequestsViewModel::class.java
    }

    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getdata()

        findNavController().navigate(R.id.filterSheet)


        fragment_sent = SentRequestsFragment()
        fragment_received = ReceivedRequestsFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragment_received).commit()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment_sent).commit()
                } else {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment_received).commit()
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
            }
        })
    }


}

interface IRequestsInteractionListener : BaseNavigator {

}
