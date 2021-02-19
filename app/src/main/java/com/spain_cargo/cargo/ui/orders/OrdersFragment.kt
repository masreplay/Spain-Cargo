package com.spain_cargo.cargo.ui.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.databinding.FragmentHomeBinding
import com.spain_cargo.cargo.databinding.FragmentOrdersBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OrdersFragment :
    BaseFragment<FragmentOrdersBinding, IOrdersInteractionListener, OrdersViewModel>(),
    IOrdersInteractionListener, IOrderItemActionListener {

    private val ordersViewModel: OrdersViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_orders
    }

    override fun getViewModel(): OrdersViewModel {
        return ordersViewModel
    }


    override fun getNavigator(): IOrdersInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getOrders("pending")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chip_pending ->
                    getViewModel().getOrders("pending")
                R.id.chip_completed ->
                    getViewModel().getOrders("completed")
                else ->
                    getViewModel().getOrders("pending")
            }
        }


        val ordersAdapter = OrdersAdapter(requireContext(), mutableListOf())
        ordersAdapter.setOnItemClickListener(this)

        getViewDataBinding().rvBrands.apply {
            adapter = ordersAdapter
        }

    }

    override fun onItemClick(item: Order) {

    }

    override fun onItemDeleteClick(item: Order) {

    }


}

interface IOrdersInteractionListener : BaseNavigator