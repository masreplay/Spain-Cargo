package com.enjaz.hr.data.model.attendance


import com.google.gson.annotations.SerializedName

data class AttendanceResponse(
    @SerializedName("days")
    val days: List<Day>,
    @SerializedName("totalDelay")
    val totalDelay: Int,
    @SerializedName("totalOvertime")
    val totalOvertime: Int,
    @SerializedName("totalWorkTime")
    val totalWorkTime: Int
)