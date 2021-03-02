package com.spain_cargo.cargo.ui.orderDetail

import androidx.hilt.lifecycle.ViewModelInject
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.ui.base.BaseViewModel

class OrderDetailViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IShowOrderInteractionListener>(
    dataManager
)