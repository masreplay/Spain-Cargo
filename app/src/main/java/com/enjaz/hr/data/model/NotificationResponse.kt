package com.enjaz.hr.data.model


import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalPassengers")
    val totalPassengers: Int
)