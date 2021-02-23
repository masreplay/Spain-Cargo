package com.spain_cargo.cargo.data.model.requests


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("money_back_request")
    val moneyBackRequest: MoneyBackRequest
)