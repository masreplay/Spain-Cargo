package com.spain_cargo.cargo.data.model.checkout


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("color")
    val color: String?,
    @SerializedName("created_at")
    val createdAt: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("notes")
    val notes: Any?,
    @SerializedName("order_id")
    val orderId: Int?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("updated_at")
    val updatedAt: Any?
)