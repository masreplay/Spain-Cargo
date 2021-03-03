package com.spain_cargo.cargo.ui.orderDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.Status
import com.spain_cargo.cargo.data.model.orders.City
import com.spain_cargo.cargo.data.model.orders.Country
import com.spain_cargo.cargo.data.model.orders.Item
import com.spain_cargo.cargo.data.model.orders.User
import com.spain_cargo.cargo.databinding.FragmentOrderDetailBinding
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.ui.base.BaseSheetFragment
import com.spain_cargo.cargo.ui.createOrder.IItemDetailActionListener
import com.spain_cargo.cargo.ui.createOrder.ItemsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailFragment :
    BaseSheetFragment<FragmentOrderDetailBinding, IOrderDetailInteractionListener, OrderDetailViewModel>(),
    IOrderDetailInteractionListener, IItemDetailActionListener {

    private val orderDetailViewModel: OrderDetailViewModel by viewModels()

    private val args by navArgs<OrderDetailFragmentArgs>()

    override fun getLayoutId() = R.layout.fragment_order_detail
    override fun getViewModel() = orderDetailViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getOrderById(args.order.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.order.relations.apply { bindOrder(user, country, city) }

        val itemsAdapter = ItemsAdapter(requireActivity(), mutableListOf()).also {
            it.setOnItemClickListener(this)
        }

        getViewDataBinding().rv.apply {
            adapter = itemsAdapter
        }

        getViewModel().orderResponse.observe(this) { resource ->
            resource?.let {
                if (it.status == Status.SUCCESS) {
                    it.data?.relations?.apply { bindOrder(user, country, city) }
                }
                if (it.status == Status.ERROR) {

                }
            }
        }

    }


    private fun bindOrder(user: User, country: Country, city: City, item: Item? = null) {
        getViewDataBinding().apply {
            user.apply {
                tvUserName.text = name
                tvUserEmail.text = email
                tvUserPhone.text = phoneNumber
                ivUser.setImageURI(imageUrl)
            }
            country.apply {
                tvCountryName.text = name
                tvCountryAmount.text = amount.toString()
                ivCountry.setImageURI(flagImageUrl)
            }
            tvCity.text = city.city
        }
    }
}


interface IOrderDetailInteractionListener : BaseNavigator
