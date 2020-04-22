package com.enjaz.university.data.model.grades


import com.google.gson.annotations.SerializedName

data class GradesResponse(
    @SerializedName("periods")
    val periods: List<Period>,
    @SerializedName("yearName")
    val yearName: String
)