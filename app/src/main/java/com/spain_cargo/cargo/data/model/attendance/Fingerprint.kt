package com.spain_cargo.cargo.data.model.attendance


import com.google.gson.annotations.SerializedName

data class Fingerprint(
    @SerializedName("order")
    val order: Int,
    @SerializedName("time")
    val time: String
)