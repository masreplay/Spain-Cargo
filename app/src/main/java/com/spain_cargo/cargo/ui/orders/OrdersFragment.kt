package com.spain_cargo.cargo.ui.orders

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.databinding.FragmentOrdersBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OrdersFragment :
    BaseFragment<FragmentOrdersBinding, IOrdersInteractionListener, OrdersViewModel>(),
    IOrdersInteractionListener, IOrderItemActionListener {

    // save the status to refresh recycler view after delete order
    private var status: String = "pending"

    private val ordersViewModel: OrdersViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_orders
    override fun getViewModel() = ordersViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewModel().getOrders(status)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ordersAdapter = OrdersAdapter(requireContext(), mutableListOf())
        ordersAdapter.setOnItemClickListener(this)

        getViewDataBinding().srl.apply {
            setOnRefreshListener {
                getViewModel().getOrders(status)
                isRefreshing = false
            }
        }

        getViewDataBinding().chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chip_pending -> {
                    status = "pending"
                    getViewModel().getOrders(status)
                }
                R.id.chip_completed -> {
                    status = "completed"
                    getViewModel().getOrders(status)
                }
                else -> {
                    status = "pending"
                    getViewModel().getOrders(status)
                }
            }
        }

        getViewDataBinding().rvOrders.apply {
            adapter = ordersAdapter
        }
    }

    override fun onItemClick(item: Order) {
        if (PrefsManager.instance?.getUser()?.data?.user?.role == "distributor") {

            findNavController().navigate(
                OrdersFragmentDirections.actionOrdersFragmentToShowOrdersFragment(item)
            )
        }
    }

    override fun onItemDeleteClick(item: Order) {

        if (item.actions.deletable)
            AlertDialog.Builder(requireContext()).apply {
                setCancelable(true)

                setMessage(getString(R.string.msg_delete))
                setPositiveButton(getString(R.string.option_yes)) { _, _ ->
                    getViewModel().deleteOrder(item.id)
                    snackbar(getString(R.string.msg_order_deleted_successfully))
                    getViewModel().getOrders(status)
                }
                setNegativeButton(getString(R.string.option_no)) { _, _ -> }
            }.create().show()
        else
            snackbar(getString(R.string.msg_un_deletable))
    }

    override fun onItemCompleteClick(id: String) {
        getViewModel().markOrderAsComplete(id)
        snackbar(getString(R.string.marked_as_completed))
    }

    override fun onItemRefundClick(id: String) {
        getViewModel().markOrderAsRefund(id)
        snackbar(getString(R.string.marked_as_refunded))
    }


}


interface IOrdersInteractionListener : BaseNavigator