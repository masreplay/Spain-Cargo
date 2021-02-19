package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)