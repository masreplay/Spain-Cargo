package com.spain_cargo.cargo.data.model.requestMoney


import com.google.gson.annotations.SerializedName

data class RequestMoneyResponse(
    @SerializedName("data")
    val `data`: Data?
)