package com.spain_cargo.cargo.data.model.orders


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Item(
    @SerializedName("color")
    val color: String,
    @SerializedName("created_at")
    val createdAt: Date?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("size")
    val size: String,
    @SerializedName("updated_at")
    val updatedAt: Date?
) : Parcelable