package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class OrderXResponse(
    @SerializedName("data")
    val data: DataX
) : Parcelable