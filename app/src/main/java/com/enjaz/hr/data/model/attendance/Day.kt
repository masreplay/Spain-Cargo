package com.enjaz.hr.data.model.attendance


import com.enjaz.hr.HRMApp
import com.enjaz.hr.R
import com.enjaz.hr.util.amPm
import com.enjaz.hr.util.toDate
import com.enjaz.hr.util.toDay
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
) {

    fun getWorkTime(): String {
        return "${dayTimeChunks[0].from.substringBeforeLast(":")
            .amPm()} - ${dayTimeChunks[0].to.substringBeforeLast(":").amPm()}"
    }

    fun getDate(): String {


        return datee.toDate()


    }

    fun getDay(): String {

        return datee.toDay()


    }

    fun getExeptionStatus(): String? {
        return if (status == "Late") {
            HRMApp.applicationContext().getString(R.string.late)
        } else ""


    }

    fun getCheckInTime(): String {
        return if (dayTimeChunks[0].fingerprints.isNotEmpty()) {
            HRMApp.applicationContext()
                .getString(R.string.check_in) + "    ${dayTimeChunks[0].fingerprints[0].time.substringBeforeLast(
                ":"
            ).amPm()}  ${getExeptionStatus()} "
        } else {
            HRMApp.applicationContext().getString(R.string.no_check_in_yet)
        }

    }

    fun getCheckOutTime(): String {
        return if (dayTimeChunks[0].fingerprints.isNotEmpty()) {
            HRMApp.applicationContext()
                .getString(R.string.check_out) + " ${dayTimeChunks[0].fingerprints[1].time.substringBeforeLast(
                ":"
            ).amPm()}   " +
                    ""
        } else {
            HRMApp.applicationContext().getString(R.string.missed_out_punch)
        }
    }
}