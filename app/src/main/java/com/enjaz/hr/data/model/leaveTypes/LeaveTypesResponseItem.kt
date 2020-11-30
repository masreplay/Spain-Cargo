package com.enjaz.hr.data.model.leaveTypes


import com.google.gson.annotations.SerializedName

data class LeaveTypesResponseItem(
    @SerializedName("effectBalanceFlag")
    val effectBalanceFlag: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("maxDuration")
    val maxDuration: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("paidFlag")
    val paidFlag: Boolean,
    @SerializedName("timeFlag")
    val timeFlag: Boolean
)