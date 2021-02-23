package com.spain_cargo.cargo.data.model.login


import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("data")
    val data: Data?
)