package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Country(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("flag_image_url")
    val flagImageUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable