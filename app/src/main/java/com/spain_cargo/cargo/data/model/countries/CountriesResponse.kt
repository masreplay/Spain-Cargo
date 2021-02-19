package com.spain_cargo.cargo.data.model.countries


import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("data")
    val `data`: Data?
)