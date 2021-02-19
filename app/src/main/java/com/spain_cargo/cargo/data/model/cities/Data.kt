package com.spain_cargo.cargo.data.model.cities


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cities")
    val cities: List<City>?
)