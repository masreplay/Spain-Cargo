package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("actions")
    val actions: Actions?,
    @SerializedName("entity")
    val entity: Entity?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("relations")
    val relations: Relations?
)