package com.enjaz.hr.data.model.balance


import com.google.gson.annotations.SerializedName

data class BalanceResponse(
    @SerializedName("balances")
    val balances: List<Balance>
)