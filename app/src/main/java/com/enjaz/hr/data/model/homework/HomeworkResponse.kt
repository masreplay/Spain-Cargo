package com.enjaz.hr.data.model.homework


import com.google.gson.annotations.SerializedName

data class HomeworkResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalCount")
    val totalCount: Int
)