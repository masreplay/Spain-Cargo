package com.spain_cargo.cargo.data.model.moneyRequests


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("money_requests")
    val moneyRequests: List<MoneyRequest>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)