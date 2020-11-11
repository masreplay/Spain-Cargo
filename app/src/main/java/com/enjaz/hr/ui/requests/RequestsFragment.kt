package com.enjaz.hr.ui.requests

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetRequestsBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.framgnet_requests.*

@AndroidEntryPoint
class RequestsFragment :
    BaseFragment<FramgnetRequestsBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()
    lateinit var fragmentSent: Fragment
    lateinit var fragmentReceived: Fragment
    override fun getLayoutId(): Int {
        return R.layout.framgnet_requests
    }

    override fun getViewModel(): RequestsViewModel {
        return requestsViewModel
    }

    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        getViewModel().getdata()


        fragmentSent = SentRequestsFragment()
        fragmentReceived = ReceivedRequestsFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentReceived).commit()

        tv_received.setOnClickListener {
            tv_received.setTextColor(Color.BLACK)
            tv_sent.setTextColor(Color.WHITE)
            tv_sent.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.bg_round_outline_ripple_16dp)
            tv_received.background =
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_round_ripple_16dp
                )
            childFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentReceived).commit()

        }

        tv_sent.setOnClickListener {
            tv_sent.setTextColor(Color.BLACK)
            tv_received.setTextColor(Color.WHITE)
            tv_sent.background =
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.bg_round_ripple_16dp
                )
            tv_received.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.bg_round_outline_ripple_16dp)
            childFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentSent).commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }
}

interface IRequestsInteractionListener : BaseNavigator {

}
