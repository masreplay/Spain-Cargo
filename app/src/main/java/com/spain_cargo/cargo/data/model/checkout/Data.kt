package com.spain_cargo.cargo.data.model.checkout


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("order")
    val order: Order?
)