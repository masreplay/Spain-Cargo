package com.enjaz.hr.data.model.homework


import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("extension")
    val extension: String,
    @SerializedName("fileType")
    val fileType: Int,
    @SerializedName("fileTypeName")
    val fileTypeName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("path")
    val path: String
)