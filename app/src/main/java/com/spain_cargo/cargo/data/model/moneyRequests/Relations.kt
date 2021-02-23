package com.spain_cargo.cargo.data.model.moneyRequests


import com.google.gson.annotations.SerializedName

data class Relations(
    @SerializedName("sender")
    val sender: Sender?
)