package com.spain_cargo.cargo.data.model.error


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: Error
)