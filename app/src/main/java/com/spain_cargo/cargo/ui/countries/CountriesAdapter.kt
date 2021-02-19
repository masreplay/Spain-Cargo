package com.spain_cargo.cargo.ui.countries

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.data.model.countries.Country
import com.spain_cargo.cargo.ui.base.BaseDataItemInteractionListener
import com.spain_cargo.cargo.ui.base.BaseDataItemsAdapter

open class CountriesAdapter(
    protected var context: Context, objects: MutableList<Country>
) : BaseDataItemsAdapter<Country>(objects, null) {


    override fun createViewDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_country,
            parent,
            false
        )
    }
}

interface INotificationItemActionListener : BaseDataItemInteractionListener {
    fun onItemClick(item: Country)
}
