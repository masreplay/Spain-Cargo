package com.enjaz.hr.data.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("airline")
    val airline: Airline,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("trips")
    val trips: Int,
    @SerializedName("__v")
    val v: Int
)