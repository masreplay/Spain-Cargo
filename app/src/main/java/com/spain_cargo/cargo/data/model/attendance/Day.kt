package com.spain_cargo.cargo.data.model.attendance


import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("date")
    val datee: String,
    @SerializedName("dayTimeChunks")
    val dayTimeChunks: List<DayTimeChunk>,
    @SerializedName("lateTime")
    val lateTime: Int,
    @SerializedName("overtime")
    val overtime: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("time")
    val time: Int
)