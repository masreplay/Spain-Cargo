package com.enjaz.hr.data.model.getLeaveRequests


import com.enjaz.hr.HRMApp
import com.enjaz.hr.R
import com.enjaz.hr.util.toDate
import com.enjaz.hr.util.toDay
import com.google.gson.annotations.SerializedName

data class LeaveRequestResponseItem(
    @SerializedName("employeeName")
    val employeeName: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("leaveDescription")
    val leaveDescription: String,
    @SerializedName("leaveName")
    val leaveName: String,
    @SerializedName("leaveStatus")
    val leaveStatus: String,
    @SerializedName("leaveTypeId")
    val leaveTypeId: Int,
    @SerializedName("numberOfDays")
    val numberOfDays: Int,
    @SerializedName("requesterUserId")
    val requesterUserId: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("workflowCorrelationId")
    val workflowCorrelationId: String,
    @SerializedName("requestCreationDate")
    val requestCreationDate: String

){

    fun getDuration():String{
        return "${startDate.toDate()} ${startDate.toDay()}   ${HRMApp.applicationContext().getString(R.string.dash)}   ${endDate.toDate()} ${endDate.toDay()} "

    }

    fun getRequestDate():String{
        return "${requestCreationDate.toDate()} ${requestCreationDate.toDay()}"

    }




}