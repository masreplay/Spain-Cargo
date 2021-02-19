package com.spain_cargo.cargo.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.brands.Brand
import com.spain_cargo.cargo.data.model.countries.Country
import com.spain_cargo.cargo.ui.base.BaseDataItemInteractionListener
import com.spain_cargo.cargo.ui.base.BaseDataItemsAdapter

open class HomeAdapter(
    protected var context: Context, objects: MutableList<Country>
) : BaseDataItemsAdapter<Country>(objects, null) {


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_brand,
            parent,
            false
        )
    }
}

interface IHomeItemActionListener : BaseDataItemInteractionListener {
    fun onItemClick(item: Brand)
}
