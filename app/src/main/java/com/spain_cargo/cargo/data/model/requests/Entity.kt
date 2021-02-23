package com.spain_cargo.cargo.data.model.requests


import com.google.gson.annotations.SerializedName
import java.util.*

data class Entity(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("created_at")
    val createdAt: Date?,
    @SerializedName("status")
    val status: String
)