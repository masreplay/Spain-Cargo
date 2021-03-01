package com.spain_cargo.cargo.data.model.transaction


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("transactions")
    val transactions: Transactions
)