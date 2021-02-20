package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Data(
    @SerializedName("orders")
    val orders: List<Order>,
    @SerializedName("pagination")
    val pagination: Pagination
) : Parcelable