package com.spain_cargo.cargo.data.model.orders


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Relations(
    @SerializedName("city")
    val city: City,
    @SerializedName("country")
    val country: Country,
    @SerializedName("user")
    val user: User
): Parcelable