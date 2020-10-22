package com.enjaz.hr.data.model.materials


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("fieldName")
    val fieldName: String,
    @SerializedName("material")
    val material: Material,
    @SerializedName("programId")
    val programId: Int
)