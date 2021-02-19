package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName

data class OrdersResponse(
    @SerializedName("data")
    val `data`: List<Order>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)