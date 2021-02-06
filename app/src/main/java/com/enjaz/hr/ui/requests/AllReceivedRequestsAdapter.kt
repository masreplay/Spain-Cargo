package com.enjaz.hr.ui.requests

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.getLeaveRequests.Item
import com.enjaz.hr.databinding.ItemReceivedAllBinding
import com.enjaz.hr.ui.base.BaseDataItemInteractionListener
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class AllReceivedRequestsAdapter(
    protected var context: Context, objects: MutableList<Item>
) : BaseDataItemsAdapter<Item>(objects, null) {

    protected lateinit var binding: ItemReceivedAllBinding

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_received_all,
            parent,
            false
        )
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}

interface IRAllRequestsItemActionListener : BaseDataItemInteractionListener {
    fun onAcceptClick(item: Item, position: Int)
    fun onDeclinedClick(item: Item, position: Int)
}

