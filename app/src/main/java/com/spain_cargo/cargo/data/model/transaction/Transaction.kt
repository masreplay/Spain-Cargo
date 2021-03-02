package com.spain_cargo.cargo.data.model.transaction


import com.google.gson.annotations.SerializedName
import java.util.*

data class Transaction(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: Date?,
    @SerializedName("user_id")
    val userId: Int
)
