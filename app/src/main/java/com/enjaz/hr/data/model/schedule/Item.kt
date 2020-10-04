package com.enjaz.hr.data.model.schedule


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("periodName")
    val periodName: String,
    @SerializedName("roomName")
    val roomName: String,
    @SerializedName("schedule")
    val schedule: Schedule,
    @SerializedName("subLectureName")
    val subLectureName: String,
    @SerializedName("lectureDuration")
    val lectureDuration: Int,
    @SerializedName("profName")
    val profName: String,
    @SerializedName("profPhoneNumber")
    val profPhoneNumber: String


)