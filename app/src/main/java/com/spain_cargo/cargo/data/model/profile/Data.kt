package com.spain_cargo.cargo.data.model.profile


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Data(
    @SerializedName("user")
    val user: User
) : Parcelable