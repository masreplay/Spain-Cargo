package com.spain_cargo.cargo.data.model.requests


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("money_back_requests")
    val moneyBackRequests: List<MoneyBackRequest>,
    @SerializedName("pagination")
    val pagination: Pagination
)