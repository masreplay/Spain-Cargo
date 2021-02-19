package com.spain_cargo.cargo.data.model.countries


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("countries")
    val countries: List<Country>?
)