package com.enjaz.university.data.model.announcements


import com.google.gson.annotations.SerializedName

data class AnnouncementResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalCount")
    val totalCount: Int
)