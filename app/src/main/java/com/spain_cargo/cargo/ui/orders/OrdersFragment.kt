package com.spain_cargo.cargo.ui.orders

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.databinding.FragmentOrdersBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.util.snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OrdersFragment :
    BaseFragment<FragmentOrdersBinding, IOrdersInteractionListener, OrdersViewModel>(),
    IOrdersInteractionListener, IOrderItemActionListener {

    private val ordersViewModel: OrdersViewModel by viewModels()


    override fun getLayoutId() = R.layout.fragment_orders

    override fun getViewModel() = ordersViewModel

    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getOrders("completed")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ordersAdapter = OrdersAdapter(requireContext(), mutableListOf())
        ordersAdapter.setOnItemClickListener(this)

        getViewDataBinding().chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chip_pending ->
                    getViewModel().getOrders("pending")
                R.id.chip_completed ->
                    getViewModel().getOrders("completed")
                else ->
                    getViewModel().getOrders("pending")
            }
        }

        getViewDataBinding().rvOrders.apply {
            adapter = ordersAdapter
        }
    }

    override fun onItemClick(item: Order) {}

    override fun onItemDeleteClick(item: Order) {

        if (item.actions.deletable)
            AlertDialog.Builder(requireContext()).apply {
                setCancelable(true)

                setMessage(getString(R.string.delete_dialog_message))
                setPositiveButton(getString(R.string.option_yes)) { _, _ ->
                    getViewModel().deleteOrder(item.id)
                    snackbar(getString(R.string.order_deleted_successfully))
                    // Todo : update recyclerview
                    // getViewModel().getOrders("completed")

                }
                setNegativeButton(getString(R.string.option_no)) { _, _ ->

                }

            }.create().show()
        else
        // TODO: remove delete icon from order card
            snackbar(getString(R.string.un_deletable_dialog_message))
    }
}


interface IOrdersInteractionListener : BaseNavigator