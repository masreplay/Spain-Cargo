package com.enjaz.hr.data.model.attendance


import com.google.gson.annotations.SerializedName

data class DayTimeChunk(
    @SerializedName("fingerprints")
    val fingerprints: List<Fingerprint>,
    @SerializedName("from")
    val from: String,
    @SerializedName("isChunkEndsNextDay")
    val isChunkEndsNextDay: Boolean,
    @SerializedName("offFrom")
    val offFrom: Any,
    @SerializedName("offTime")
    val offTime: Int,
    @SerializedName("offTo")
    val offTo: Any,
    @SerializedName("requiredFPCount")
    val requiredFPCount: Int,
    @SerializedName("to")
    val to: String
)
{
    fun getTime():String{

        return "$from - $to"
    }
}