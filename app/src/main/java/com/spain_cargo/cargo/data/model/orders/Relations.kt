package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName

data class Relations(
    @SerializedName("city")
    val city: City?,
    @SerializedName("country")
    val country: Country?
)