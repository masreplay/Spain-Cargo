package com.spain_cargo.cargo.ui.moneyRB

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.requests.MoneyBackRequest
import com.spain_cargo.cargo.ui.base.BaseDataItemInteractionListener
import com.spain_cargo.cargo.ui.base.BaseDataItemsAdapter

open class MoneyRbAdapter(
    protected var context: Context, objects: MutableList<MoneyBackRequest>
) : BaseDataItemsAdapter<MoneyBackRequest>(objects, null) {


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_money_rb,
            parent,
            false
        )
    }
}

interface IMoneyRbItemActionListener : BaseDataItemInteractionListener {
    fun onItemClick(item: MoneyBackRequest)
    fun onItemAcceptClick(id: Int)
    fun onItemRejectedClick(id: Int)
}
