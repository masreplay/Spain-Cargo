
package com.enjaz.hr.data.model.profile

import com.google.gson.annotations.SerializedName

data class BasicInformation (
	@SerializedName("email") val email : String,
	@SerializedName("employeeNo") val employeeNo : Int,
	@SerializedName("phone") val phone : String,
	@SerializedName("birthDate") val birthDate : String,
	@SerializedName("age") val age : Int,
	@SerializedName("address") val address : String,
	@SerializedName("maritalStatus") val maritalStatus : String,
	@SerializedName("name") val name : String
)