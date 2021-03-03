package com.spain_cargo.cargo.ui.moneyRB

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.requests.MoneyBackRequest
import com.spain_cargo.cargo.databinding.FragmentMoneyRbBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoneyRBFragment :
    BaseFragment<FragmentMoneyRbBinding, IMoneyRBInteractionListener, MoneyRBViewModel>(),
    IMoneyRBInteractionListener, IMoneyRbItemActionListener {

    private val addItemViewModel: MoneyRBViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_money_rb
    override fun getViewModel() = addItemViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getMoneyRB()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moneyRbAdapter = MoneyRbAdapter(requireContext(), mutableListOf()).also {
            it.setOnItemClickListener(this)
        }

        getViewDataBinding().srl.apply {
            setOnRefreshListener {
                getViewModel().getMoneyRB()
                isRefreshing = false
            }
        }

        getViewDataBinding().rvMoneyRb.adapter = moneyRbAdapter
    }

    override fun onItemClick(item: MoneyBackRequest) {}

    override fun onItemAcceptClick(id: Int) {
        getViewModel().markMoneyBrAsAccepted(id)
        getViewModel().getMoneyRB()
    }

    override fun onItemRejectedClick(id: Int) {
        getViewModel().markMoneyBrAsRejected(id)
        getViewModel().getMoneyRB()
    }

}

interface IMoneyRBInteractionListener : BaseNavigator