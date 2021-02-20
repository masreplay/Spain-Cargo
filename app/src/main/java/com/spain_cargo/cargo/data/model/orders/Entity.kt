package com.spain_cargo.cargo.data.model.orders


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Entity(
    @SerializedName("address")
    val address: String,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("amount_delivery")
    val amountDelivery: Int,
    @SerializedName("completed_by_user_id")
    val completedByUserId: String?,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String
) : Parcelable