package com.spain_cargo.cargo.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.transaction.Link
import com.spain_cargo.cargo.data.model.transaction.Transaction
import com.spain_cargo.cargo.ui.base.BaseDataItemInteractionListener
import com.spain_cargo.cargo.ui.base.BaseDataItemsAdapter

open class TransactionAdapter(
    protected var context: Context, objects: MutableList<Link>
) : BaseDataItemsAdapter<Link>(objects, null) {


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_transaction,
            parent,
            false
        )
    }
}

interface ILinkItemActionListener : BaseDataItemInteractionListener {
    fun onItemClick(item: Transaction)
}
