package com.spain_cargo.cargo.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.ui.base.BaseViewModel

class MainViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IMainInteractionListener>(
    dataManager
)
