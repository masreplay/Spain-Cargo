package com.spain_cargo.cargo.data.model.checkout


import com.google.gson.annotations.SerializedName

data class Actions(
    @SerializedName("completable")
    val completable: Boolean?,
    @SerializedName("deletable")
    val deletable: Boolean?,
    @SerializedName("refundable")
    val refundable: Boolean?
)