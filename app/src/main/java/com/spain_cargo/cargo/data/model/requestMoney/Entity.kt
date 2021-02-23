package com.spain_cargo.cargo.data.model.requestMoney


import com.google.gson.annotations.SerializedName

data class Entity(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("from")
    val from: Any?,
    @SerializedName("is_back_request")
    val isBackRequest: Any?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("taxes")
    val taxes: Int?,
    @SerializedName("transferring_type")
    val transferringType: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)