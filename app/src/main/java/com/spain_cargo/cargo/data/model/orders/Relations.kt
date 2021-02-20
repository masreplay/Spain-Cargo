package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

data class Relations(
    @SerializedName("city")
    val city: City,
    @SerializedName("country")
    val country: Country
)