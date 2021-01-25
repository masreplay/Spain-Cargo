package com.enjaz.hr.ui.sendRequest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.hr.R
import com.enjaz.hr.data.model.Types
import com.enjaz.hr.ui.base.BaseDataItemInteractionListener
import com.enjaz.hr.ui.base.BaseDataItemsAdapter

open class RequsetMainTypesAdapter(
    protected var context: Context, objects: MutableList<Types>
) : BaseDataItemsAdapter<Types>(objects, null) {


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_request_type,
            parent,
            false
        )
    }


}

interface ITypeItemActionListener : BaseDataItemInteractionListener {
    fun onTypeClick(type: Types)
}

