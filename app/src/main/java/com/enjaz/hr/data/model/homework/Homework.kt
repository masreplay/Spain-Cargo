package com.enjaz.hr.data.model.homework


import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale


data class Homework(
    @SerializedName("deadline")
    val deadline: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("periodId")
    val periodId: Int,
    @SerializedName("subLectureId")
    val subLectureId: Int
) {
    public fun getDate(): String {

        val outputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val inputFormat: DateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

        val date: Date = inputFormat.parse(this.deadline)

        return (outputFormat.format(date))
    }
}