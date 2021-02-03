package com.enjaz.hr.data.model.getLeaveRequests


import com.google.gson.annotations.SerializedName

data class LeavesResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalCount")
    val totalCount: Int
)