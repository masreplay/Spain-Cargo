package com.enjaz.university.data.model.token

import com.google.gson.annotations.SerializedName

data class TokenResult (

	@SerializedName("accessToken") val accessToken : String,
	@SerializedName("encryptedAccessToken") val encryptedAccessToken : String,
	@SerializedName("expireInSeconds") val expireInSeconds : Int,
	@SerializedName("shouldResetPassword") val shouldResetPassword : Boolean,
	@SerializedName("passwordResetCode") val passwordResetCode : String,
	@SerializedName("userId") val userId : Int,
	@SerializedName("requiresTwoFactorVerification") val requiresTwoFactorVerification : Boolean,
	@SerializedName("twoFactorAuthProviders") val twoFactorAuthProviders : String,
	@SerializedName("twoFactorRememberClientToken") val twoFactorRememberClientToken : String,
	@SerializedName("returnUrl") val returnUrl : String,
	@SerializedName("refreshToken") val refreshToken : String,
	@SerializedName("refreshTokenExpireInSeconds") val refreshTokenExpireInSeconds : Int
)