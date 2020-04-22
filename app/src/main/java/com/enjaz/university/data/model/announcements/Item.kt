package com.enjaz.university.data.model.announcements


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("periodId")
    val periodId: Int,
    @SerializedName("periodName")
    val periodName: String,
    @SerializedName("referenceId")
    val referenceId: Int,
    @SerializedName("referenceType")
    val referenceType: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("type")
    val type: Type
)