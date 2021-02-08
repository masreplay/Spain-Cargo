package com.enjaz.hr.data.model.home


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Teammate(
    @SerializedName("departmentName")
    val departmentName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("pictureUrl")
    val pictureUrl: String
) : Serializable