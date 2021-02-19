package com.spain_cargo.cargo.data.model


import com.google.gson.annotations.SerializedName

data class CreateOrder(
    @SerializedName("address")
    val address: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("city_id")
    val city_id: Int?,
    @SerializedName("country_id")
    val country_id: Int,
    @SerializedName("items")
    val items: List<Item>
)