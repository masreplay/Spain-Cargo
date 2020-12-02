package com.enjaz.hr.data.model.requestsTypes


import com.google.gson.annotations.SerializedName

data class RequestTypesResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalCount")
    val totalCount: Int
)