package com.spain_cargo.cargo.data.model.cities


import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("data")
    val `data`: Data?
)