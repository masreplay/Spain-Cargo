package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

data class Actions(
    @SerializedName("completable")
    val completable: Boolean,
    @SerializedName("deletable")
    val deletable: Boolean,
    @SerializedName("refundable")
    val refundable: Boolean
)