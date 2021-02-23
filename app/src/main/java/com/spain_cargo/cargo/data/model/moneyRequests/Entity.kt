package com.spain_cargo.cargo.data.model.moneyRequests


import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Entity(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("is_back_request")
    val isBackRequest: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("taxes")
    val taxes: Int?,
    @SerializedName("transferring_type")
    val transferringType: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
) {
    fun getDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return formatter.format(createdAt)
    }
}