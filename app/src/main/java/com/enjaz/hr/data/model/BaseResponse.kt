package com.enjaz.hr.data.model

import com.google.gson.annotations.SerializedName


data class BaseResponse<T> (

	@SerializedName("result") val result : T,
	@SerializedName("targetUrl") val targetUrl : String,
	@SerializedName("success") val success : Boolean,
	@SerializedName("error") val error : Error,
	@SerializedName("unAuthorizedRequest") val unAuthorizedRequest : Boolean
)