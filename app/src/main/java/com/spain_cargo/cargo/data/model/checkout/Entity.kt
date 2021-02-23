package com.spain_cargo.cargo.data.model.checkout


import com.google.gson.annotations.SerializedName

data class Entity(
    @SerializedName("address")
    val address: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("amount_delivery")
    val amountDelivery: Int?,
    @SerializedName("completed_by_user_id")
    val completedByUserId: Any?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)