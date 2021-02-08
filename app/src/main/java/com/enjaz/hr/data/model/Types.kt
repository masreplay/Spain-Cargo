package com.enjaz.hr.data.model

import androidx.annotation.StringRes
import com.enjaz.hr.HRMApp

data class Types(
    @StringRes
    val type: Int
) {
    fun getType(): String {
        return HRMApp.applicationContext().getString(type)
    }
}