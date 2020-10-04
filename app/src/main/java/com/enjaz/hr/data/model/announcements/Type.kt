package com.enjaz.hr.data.model.announcements


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("color")
    val color: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)