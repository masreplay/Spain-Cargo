package com.enjaz.hr.data.model.profile

import com.enjaz.hr.data.model.profile.BasicInformation
import com.enjaz.hr.data.model.profile.WorkInformation
import com.google.gson.annotations.SerializedName

data class UserInfo (

	@SerializedName("profilePictureUrl") val profilePictureUrl : String,
	@SerializedName("basicInformation") val basicInformation : BasicInformation,
	@SerializedName("workInformation") val workInformation : WorkInformation
)