package com.spain_cargo.cargo.data.model.requests


import com.google.gson.annotations.SerializedName

data class MoneyBackRequest(
    @SerializedName("actions")
    val actions: Actions,
    @SerializedName("entity")
    val entity: Entity,
    @SerializedName("id")
    val id: Int?
)