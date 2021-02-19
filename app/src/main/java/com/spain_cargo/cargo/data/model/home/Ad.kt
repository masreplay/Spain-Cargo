package com.spain_cargo.cargo.data.model.home


import com.google.gson.annotations.SerializedName

data class Ad(
    @SerializedName("name")
    val name: String,
    @SerializedName("speciality")
    val speciality: String
)