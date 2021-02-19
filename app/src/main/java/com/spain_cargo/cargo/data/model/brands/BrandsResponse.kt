package com.spain_cargo.cargo.data.model.brands


import com.google.gson.annotations.SerializedName

data class BrandsResponse(
    @SerializedName("data")
    val `data`: Data?
)