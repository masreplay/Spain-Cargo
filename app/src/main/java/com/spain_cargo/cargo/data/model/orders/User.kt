package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("date_of_birth")
    val dateOfBirth: Date,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Date?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("two_factor_recovery_codes")
    val twoFactorRecoveryCodes: String?,
    @SerializedName("two_factor_secret")
    val twoFactorSecret: String?,
    @SerializedName("updated_at")
    val updatedAt: Date?
)