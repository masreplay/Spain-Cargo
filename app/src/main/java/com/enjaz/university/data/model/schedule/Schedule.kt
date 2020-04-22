package com.enjaz.university.data.model.schedule


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("day")
    val day: String,
    @SerializedName("formTime")
    val formTime: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("periodId")
    val periodId: String,
    @SerializedName("roomId")
    val roomId: String,
    @SerializedName("subLectureId")
    val subLectureId: String,
    @SerializedName("toTime")
    val toTime: String
)