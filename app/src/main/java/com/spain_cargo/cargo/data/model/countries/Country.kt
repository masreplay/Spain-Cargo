package com.spain_cargo.cargo.data.model.countries


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?
)