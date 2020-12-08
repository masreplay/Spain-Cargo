package com.enjaz.hr.data.model.salary


import com.google.gson.annotations.SerializedName

data class SalaryDetailsResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalCount")
    val totalCount: Int
)