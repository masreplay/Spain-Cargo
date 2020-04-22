package com.enjaz.university.data.model.schedule


import com.google.gson.annotations.SerializedName

public data class ScheduleResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalCount")
    val totalCount: Int
)