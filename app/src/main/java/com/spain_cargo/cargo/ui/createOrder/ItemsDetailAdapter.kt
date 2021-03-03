package com.spain_cargo.cargo.ui.createOrder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.orders.Item

import com.spain_cargo.cargo.ui.base.BaseDataItemInteractionListener
import com.spain_cargo.cargo.ui.base.BaseDataItemsAdapter

open class ItemsDetailAdapter(
    protected var context: Context, objects: MutableList<Item>
) : BaseDataItemsAdapter<Item>(objects, null) {


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_order_detail,
            parent,
            false
        )
    }
}

interface IItemDetailActionListener : BaseDataItemInteractionListener
