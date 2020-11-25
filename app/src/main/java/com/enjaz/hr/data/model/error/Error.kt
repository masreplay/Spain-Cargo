package com.enjaz.hr.data.model.error


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("details")
    val details: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("validationErrors")
    val validationErrors: List<ValidationError>
)