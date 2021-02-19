package com.spain_cargo.cargo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("link")
    var url: String = "",
    @SerializedName("price")
    var price: Double = 0.0,
    @SerializedName("size")
    var size: String = "",
    @SerializedName("color")
    var color: String = "",
    @SerializedName("quantity")
    var quantity: Int = 1,
    @SerializedName("notes")
    var notes: String? = null
) {

}