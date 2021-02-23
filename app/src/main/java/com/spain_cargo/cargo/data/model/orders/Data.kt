package com.spain_cargo.cargo.data.model.orders


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Data(
    @SerializedName("orders")
    val orders: List<Order>?,
    @SerializedName("pagination")
    val pagination: Pagination
)