package com.spain_cargo.cargo.data.model.brands


import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("country_id")
    val countryId: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)