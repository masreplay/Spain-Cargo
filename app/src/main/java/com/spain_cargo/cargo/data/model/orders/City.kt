package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class City(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable