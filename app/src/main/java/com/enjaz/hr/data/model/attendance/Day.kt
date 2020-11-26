package com.enjaz.hr.data.model.attendance


import com.enjaz.hr.HRMApp
import com.enjaz.hr.R
import com.enjaz.hr.util.amPm
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

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
) {
    fun getDate(): String {


        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val apiDate = parser.parse(datee)

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = sdf.format(apiDate)
        return date


    }

    fun getCheckInTime(): String {
        return HRMApp.applicationContext().getString(R.string.check_in) +"    ${ dayTimeChunks[0].fingerprints[0].time.substringBeforeLast(":")} ${time.amPm()} ($status) "

    }

    fun getCheckOutTime(): String {
        return HRMApp.applicationContext().getString(R.string.check_out) +" ${ dayTimeChunks[0].fingerprints[1].time.substringBeforeLast(":")} ${time.amPm()}  "

    }
}