package com.enjaz.hr.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.balance.Balance
import com.enjaz.hr.databinding.ItemBalanceBinding
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class BalanceAdapter(protected var context: Context, objects: MutableList<Balance>) :
    BaseDataItemsAdapter<Balance>(objects, null) {

    protected lateinit var binding: ItemBalanceBinding


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_balance,
            parent,
            false
        )


    }


}

