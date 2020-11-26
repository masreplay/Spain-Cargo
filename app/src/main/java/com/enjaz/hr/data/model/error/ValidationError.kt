package com.enjaz.hr.data.model.error


import com.google.gson.annotations.SerializedName

data class ValidationError(
    @SerializedName("members")
    val members: List<String>,
    @SerializedName("message")
    val message: String
)