package com.enjaz.hr.data.model.error


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("File")
    val `file`: String,
    @SerializedName("InvariantSeverity")
    val invariantSeverity: String,
    @SerializedName("Line")
    val line: String,
    @SerializedName("MessageText")
    val messageText: String,
    @SerializedName("Position")
    val position: Int,
    @SerializedName("Routine")
    val routine: String,
    @SerializedName("Severity")
    val severity: String,
    @SerializedName("SqlState")
    val sqlState: String
)