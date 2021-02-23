package com.spain_cargo.cargo.data.model.requestMoney


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("money_request")
    val moneyRequest: MoneyRequest?
)