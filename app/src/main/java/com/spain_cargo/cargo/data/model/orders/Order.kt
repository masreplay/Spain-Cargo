package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Order(
    @SerializedName("actions")
    val actions: Actions,
    @SerializedName("entity")
    val entity: Entity,
    @SerializedName("id")
    val id: String,
    @SerializedName("relations")
    val relations: Relations
):Parcelable