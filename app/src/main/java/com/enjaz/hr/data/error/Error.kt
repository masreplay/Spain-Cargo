package com.enjaz.hr.data.error


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("error")
    val error: String,
    @SerializedName("error_description")
    val errorDescription: String
)