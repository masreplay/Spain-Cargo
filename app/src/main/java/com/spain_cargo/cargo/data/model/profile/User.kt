package com.spain_cargo.cargo.data.model.profile


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class User(
    @SerializedName("balance")
    val balance: Balance,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
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
    val updatedAt: String
) : Parcelable{
    fun getDate():String{
        val formatter= SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return formatter.format(createdAt)
    }

}


