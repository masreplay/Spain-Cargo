package com.spain_cargo.cargo.ui.moneyRequests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.moneyRequests.MoneyRequest
import com.spain_cargo.cargo.databinding.FragmentMoneyRequestsBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoneyRequestsFragment : BaseFragment<FragmentMoneyRequestsBinding, IMoneyRequestsInteractionListener, MoneyRequestsViewModel>(),
    IMoneyRequestsInteractionListener, IRequestItemActionListener {

    private val moneyRequestsViewModel: MoneyRequestsViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_money_requests
    override fun getViewModel() = moneyRequestsViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getMoneyRequests()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moneyRequestsAdapter = MoneyRequestsAdapter(requireContext(), mutableListOf())
        moneyRequestsAdapter.setOnItemClickListener(this)

        getViewDataBinding().srl.apply {
            setOnRefreshListener {
                getViewModel().getMoneyRequests()
                isRefreshing = false
            }
        }

        getViewDataBinding().rv.apply {
            adapter = moneyRequestsAdapter
        }
    }

    override fun onItemClick(item: MoneyRequest) {}

    override fun onAccept(item: MoneyRequest) {
        getViewModel().acceptMoneyRequest(item.id.toString())
    }

    override fun onDecline(item: MoneyRequest) {
        getViewModel().rejectMoneyRequest(item.id.toString())
    }


}

interface IMoneyRequestsInteractionListener : BaseNavigator