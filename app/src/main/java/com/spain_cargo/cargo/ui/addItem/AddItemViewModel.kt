package com.spain_cargo.cargo.ui.addItem

import androidx.hilt.lifecycle.ViewModelInject
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.Item
import com.spain_cargo.cargo.ui.base.BaseViewModel

class AddItemViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IAddItemInteractionListener>(
    dataManager
) {


    fun insertItem(item: Item) {
        dataManager.insertItem(item)
    }


}