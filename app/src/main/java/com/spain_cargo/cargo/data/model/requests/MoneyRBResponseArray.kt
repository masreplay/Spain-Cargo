package com.spain_cargo.cargo.data.model.requests


import com.google.gson.annotations.SerializedName

data class MoneyRBResponseArray(
    @SerializedName("data")
    val data: DataX
)