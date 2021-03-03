package com.spain_cargo.cargo.ui.orderDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.databinding.FragmentOrderDetailBinding
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.ui.base.BaseSheetFragment
import com.spain_cargo.cargo.util.print
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OrderDetailFragment :
    BaseSheetFragment<FragmentOrderDetailBinding, IShowOrderInteractionListener, OrderDetailViewModel>(),
    IShowOrderInteractionListener {

    private val orderDetailViewModel: OrderDetailViewModel by viewModels()

    private val args by navArgs<OrderDetailFragmentArgs>()

    override fun getLayoutId() = R.layout.fragment_order_detail
    override fun getViewModel() = orderDetailViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getOrder(args.orderId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().apply {
            tv.text.print()
        }
    }
}


interface IShowOrderInteractionListener : BaseNavigator