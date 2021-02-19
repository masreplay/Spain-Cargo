package com.spain_cargo.cargo.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import com.spain_cargo.cargo.data.AppDataManager
import com.spain_cargo.cargo.ui.base.BaseViewModel

class ProfileViewModel @ViewModelInject constructor(
    dataManager: AppDataManager
) : BaseViewModel<IProfileInteractionListener>(
    dataManager
)