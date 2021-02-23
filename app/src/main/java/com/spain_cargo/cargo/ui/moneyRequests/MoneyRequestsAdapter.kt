package com.spain_cargo.cargo.ui.moneyRequests

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.brands.Brand
import com.spain_cargo.cargo.data.model.countries.Country
import com.spain_cargo.cargo.data.model.moneyRequests.MoneyRequest
import com.spain_cargo.cargo.ui.base.BaseDataItemInteractionListener
import com.spain_cargo.cargo.ui.base.BaseDataItemsAdapter

open class MoneyRequestsAdapter(
    protected var context: Context, objects: MutableList<MoneyRequest>
) : BaseDataItemsAdapter<MoneyRequest>(objects, null) {


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_money_request,
            parent,
            false
        )
    }
}

interface IRequestItemActionListener : BaseDataItemInteractionListener {
    fun onItemClick(item: MoneyRequest)
    fun onAccept(item: MoneyRequest)
    fun onDecline(item: MoneyRequest)
}
