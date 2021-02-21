package com.spain_cargo.cargo.data.model.countries


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import java.util.*

data class Country(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("code")
    val code: String,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("flag_image_url")
    val flagImageUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: Date
)