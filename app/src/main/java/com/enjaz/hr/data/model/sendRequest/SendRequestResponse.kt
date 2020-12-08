package com.enjaz.hr.data.model.sendRequest


import com.google.gson.annotations.SerializedName

data class SendRequestResponse(
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("leaveDescription")
    val leaveDescription: String,
    @SerializedName("leaveName")
    val leaveName: String,
    @SerializedName("leaveTypeId")
    val leaveTypeId: Int,
    @SerializedName("startDate")
    val startDate: String
)