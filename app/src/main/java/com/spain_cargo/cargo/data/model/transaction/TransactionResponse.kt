package com.spain_cargo.cargo.data.model.transaction


import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("data")
    val data: Data
)