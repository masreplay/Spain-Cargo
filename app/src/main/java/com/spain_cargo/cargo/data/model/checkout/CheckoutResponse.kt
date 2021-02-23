package com.spain_cargo.cargo.data.model.checkout


import com.google.gson.annotations.SerializedName

data class CheckoutResponse(
    @SerializedName("data")
    val `data`: Data?
)