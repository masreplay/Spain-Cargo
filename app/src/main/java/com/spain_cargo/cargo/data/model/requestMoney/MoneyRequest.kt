package com.spain_cargo.cargo.data.model.requestMoney


import com.google.gson.annotations.SerializedName

data class MoneyRequest(
    @SerializedName("actions")
    val actions: Actions?,
    @SerializedName("entity")
    val entity: Entity?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("relations")
    val relations: List<Any>?
)