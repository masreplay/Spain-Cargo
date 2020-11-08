package com.enjaz.hr.data.model


import com.google.gson.annotations.SerializedName

data class Airline(
    @SerializedName("country")
    val country: String,
    @SerializedName("established")
    val established: String,
    @SerializedName("head_quaters")
    val headQuaters: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("slogan")
    val slogan: String,
    @SerializedName("website")
    val website: String
)