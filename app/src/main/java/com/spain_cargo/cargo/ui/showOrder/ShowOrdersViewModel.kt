package com.spain_cargo.cargo.ui.showOrder

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.data.model.orders.OrdersResponse
import com.spain_cargo.cargo.ui.base.BaseViewModel

class ShowOrdersViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IShowOrderInteractionListener>(
    dataManager
)