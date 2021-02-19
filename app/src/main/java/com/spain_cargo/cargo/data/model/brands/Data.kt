package com.spain_cargo.cargo.data.model.brands


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("brands")
    val brands: List<Brand>?
)