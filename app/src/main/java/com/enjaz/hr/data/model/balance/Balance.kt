package com.enjaz.hr.data.model.balance


import com.enjaz.hr.HRMApp
import com.enjaz.hr.R
import com.google.gson.annotations.SerializedName

data class Balance(
    @SerializedName("isTime")
    val isTime: Boolean,
    @SerializedName("leaveTypeName")
    val leaveTypeName: String,
    @SerializedName("remaining")
    val remaining: Int,
    @SerializedName("taken")
    val taken: Int
) {
    fun getTotal(): String {

        return "${remaining + taken} $leaveTypeName"
    }

    fun getTotalInt(): Int {

        return remaining + taken
    }

    fun getRemain(): String {
        if (isTime) {
            return "${HRMApp.applicationContext().getString(R.string.remaining)} $remaining ${HRMApp.applicationContext().getString(R.string.hours)}"
        } else {
            return "${HRMApp.applicationContext().getString(R.string.remaining)} $remaining ${HRMApp.applicationContext().getString(R.string.days)}"
        }
    }
}