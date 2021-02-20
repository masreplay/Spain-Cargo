package com.spain_cargo.cargo.ui.orders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.ui.base.BaseDataItemInteractionListener
import com.spain_cargo.cargo.ui.base.BaseDataItemsAdapter

open class OrdersAdapter(
    protected var context: Context, objects: MutableList<Order>
) : BaseDataItemsAdapter<Order>(objects, null) {


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_order,
            parent,
            false
        )
    }
}

interface IOrderItemActionListener : BaseDataItemInteractionListener {
    fun onItemClick(item: Order)
    fun onItemDeleteClick(item: Order)

}
