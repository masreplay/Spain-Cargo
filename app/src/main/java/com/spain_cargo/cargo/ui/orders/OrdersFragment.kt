package com.spain_cargo.cargo.ui.orders

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.login.User.Companion.USER
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.data.model.orders.OrdersResponse
import com.spain_cargo.cargo.data.model.orders.OrdersResponse.Companion.COMPLETED
import com.spain_cargo.cargo.data.model.orders.OrdersResponse.Companion.PENDING
import com.spain_cargo.cargo.databinding.FragmentOrdersBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.snackbar
import dagger.hilt.android.AndroidEntryPoint

// TODO: add pagination
@AndroidEntryPoint
class OrdersFragment :
    BaseFragment<FragmentOrdersBinding, IOrdersInteractionListener, OrdersViewModel>(),
    IOrdersInteractionListener, IOrderItemActionListener {

    private val ordersViewModel: OrdersViewModel by viewModels()

    private lateinit var orderAdapter: OrdersAdapter
    var orders = mutableListOf<Order>()

    private var status: String = PENDING
    private var page: Int = 1
    private var maxPage: Int = 0
    private var isLoading = false

    override fun getLayoutId() = R.layout.fragment_orders
    override fun getViewModel() = ordersViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getOrders(status, page)
        orderAdapter = OrdersAdapter(requireContext(), orders).also {
            it.setOnItemClickListener(this)
            it.setHasStableIds(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        getViewDataBinding().srl.apply {
            setOnRefreshListener {
                getViewModel().getOrders(status, page)
                isRefreshing = false
            }
        }

        getViewDataBinding().rvOrders.apply {
            adapter = orderAdapter
            layoutManager = lm

            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount: Int = lm.childCount
                    val totalItemCount: Int = lm.itemCount
                    val firstVisibleItemPosition: Int = lm.findFirstVisibleItemPosition()

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && page + 1 <= maxPage
                        && !isLoading
                    ) {
                        isLoading = true
                        page += 1

                        getViewModel().getOrders(status, page)
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }

        getViewDataBinding().chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chip_pending -> {
                    status = PENDING
                    getViewModel().getOrders(status, page)
                }
                R.id.chip_completed -> {
                    status = COMPLETED
                    getViewModel().getOrders(status, page)
                }
                else -> {
                    status = PENDING
                    getViewModel().getOrders(status, page)
                }
            }
        }
    }

    override fun onSuccess(ordersResponse: OrdersResponse) {
        maxPage = ordersResponse.data.pagination.count
        ordersResponse.data.orders?.let { orders.addAll(it) }
        orderAdapter.setItemsList(orders)
        isLoading = false
    }

    override fun onItemClick(item: Order) {
        if (PrefsManager.instance?.getUser()?.data?.user?.role != USER) {
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
                    getViewModel().getOrders(status, page)
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

interface IOrdersInteractionListener : BaseNavigator {
    fun onSuccess(ordersResponse: OrdersResponse)
}