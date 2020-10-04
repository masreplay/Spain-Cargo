package com.enjaz.hr.data.model

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    val code: Int,
    @SerializedName("details")
    val details: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("validationErrors")
    val validationErrors: String
)
