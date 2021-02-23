package com.spain_cargo.cargo.data.model.profile


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.spain_cargo.cargo.util.toFormat
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Balance(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("payment_key")
    val paymentKey: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
) : Parcelable {
    val formattedAmount get() = amount.toFormat()


}