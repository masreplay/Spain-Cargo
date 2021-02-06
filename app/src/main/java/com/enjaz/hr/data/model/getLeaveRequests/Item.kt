package com.enjaz.hr.data.model.getLeaveRequests


import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Item(
    @SerializedName("approverId")
    val approverId: Int?,
    @SerializedName("employeeName")
    val employeeName: String?,
    @SerializedName("endDate")
    val endDate: Date?,
    @SerializedName("requestCreationDate")
    val requestCreationDate: Date,
    @SerializedName("requestType")
    val requestType: String?,
    @SerializedName("requesterId")
    val requesterId: Int?,
    @SerializedName("startDate")
    val startDate: Date?,
    @SerializedName("workflowCorrelationId")
    val workflowCorrelationId: String,
    @SerializedName("workflowStatus")
    val workflowStatus: String?
) {
    fun getDuration(): String {
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.US)
        return if (startDate != null && endDate != null)
            "${formatter.format(startDate)} - ${formatter.format(endDate)}"
        else if (startDate == null && endDate != null)
            formatter.format(endDate)
        else if (startDate != null && endDate == null)
            formatter.format(startDate)
        else
            "No Date Set"
    }

    fun getRequestCreationDate(): String {
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.US)
        return formatter.format(requestCreationDate)
    }

    fun getReqType(): String? {
        return requestType?.replace("\\d+", "")?.replace("([A-Z])", "$1 ")
    }
}