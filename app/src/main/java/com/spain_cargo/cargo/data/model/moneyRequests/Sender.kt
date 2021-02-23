package com.spain_cargo.cargo.data.model.moneyRequests


import com.google.gson.annotations.SerializedName

data class Sender(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("date_of_birth")
    val dateOfBirth: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("two_factor_recovery_codes")
    val twoFactorRecoveryCodes: Any?,
    @SerializedName("two_factor_secret")
    val twoFactorSecret: Any?,
    @SerializedName("updated_at")
    val updatedAt: String?
)