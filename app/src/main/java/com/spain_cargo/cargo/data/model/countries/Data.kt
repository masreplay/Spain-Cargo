package com.spain_cargo.cargo.data.model.countries


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

data class Data(
    @SerializedName("countries")
    val countries: List<Country>
)