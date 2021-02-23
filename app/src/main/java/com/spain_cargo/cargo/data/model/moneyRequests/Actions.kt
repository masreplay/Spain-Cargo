package com.spain_cargo.cargo.data.model.moneyRequests


import com.google.gson.annotations.SerializedName

data class Actions(
    @SerializedName("acceptable")
    val acceptable: Boolean?,
    @SerializedName("rejectable")
    val rejectable: Boolean?
)