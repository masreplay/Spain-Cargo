package com.enjaz.hr.ui.requests

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.getLeaveRequests.Item
import com.enjaz.hr.data.model.getLeaveRequests.LeaveRequestResponseItem
import com.enjaz.hr.data.model.getLeaveRequests.LeavesResponse
import com.enjaz.hr.databinding.ItemSentBinding
import com.enjaz.hr.ui.base.BaseDataItemInteractionListener
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class SentRequestsAdapter(
    protected var context: Context, val objects: MutableList<Item>
) : BaseDataItemsAdapter<Item>(objects, null) {

    protected lateinit var binding: ItemSentBinding

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_sent,
            parent,
            false
        )
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}

interface ISRequestsItemActionListener : BaseDataItemInteractionListener {
    fun onCancelClick(leave: Item)
}
