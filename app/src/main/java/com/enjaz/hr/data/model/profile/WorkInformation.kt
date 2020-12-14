package com.enjaz.hr.data.model.profile

import com.google.gson.annotations.SerializedName
data class WorkInformation (
	@SerializedName("department") val department : String,
	@SerializedName("reportingTo") val reportingTo : String,
	@SerializedName("joinDate") val joinDate : String,
	@SerializedName("employeeStatus") val employeeStatus : String,
	@SerializedName("role") val role : String,
	@SerializedName("workPhone") val workPhone : String,
	@SerializedName("experience") val experience : String,
	@SerializedName("jobTitle") val jobTitle : String
)