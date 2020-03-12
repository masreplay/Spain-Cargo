package com.enjaz.university.data.model

import com.google.gson.annotations.SerializedName


data class BaseResponse<T> (

	@SerializedName("result") val result : T,
	@SerializedName("targetUrl") val targetUrl : String,
	@SerializedName("success") val success : Boolean,
	@SerializedName("error") val error : String,
	@SerializedName("unAuthorizedRequest") val unAuthorizedRequest : Boolean
)