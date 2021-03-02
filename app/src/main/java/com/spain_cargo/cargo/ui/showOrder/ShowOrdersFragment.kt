package com.spain_cargo.cargo.ui.showOrder

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
import com.spain_cargo.cargo.util.snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowOrdersFragment :
    BaseFragment<FragmentOrdersBinding, IShowOrderInteractionListener, ShowOrdersViewModel>(),
    IShowOrderInteractionListener {

    private val ordersViewModel: ShowOrdersViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_show_order
    override fun getViewModel() = ordersViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}


interface IShowOrderInteractionListener : BaseNavigator