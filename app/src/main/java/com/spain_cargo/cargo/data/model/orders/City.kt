package com.spain_cargo.cargo.data.model.orders


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable {

    val city get() = "$name : $amount"

}