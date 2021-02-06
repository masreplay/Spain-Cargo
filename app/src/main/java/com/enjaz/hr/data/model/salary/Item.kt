package com.enjaz.hr.data.model.salary


import com.enjaz.hr.util.toFormat
import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


data class Item(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("creationTime")
    val creationTime: String,
    @SerializedName("creatorId")
    val creatorId: String,
    @SerializedName("dateTime")
    val dateTime: String,//يوم استلام الراتب
    @SerializedName("deleterId")
    val deleterId: String,
    @SerializedName("deletionTime")
    val deletionTime: String,
    @SerializedName("employeeId")
    val employeeId: Int,
    @SerializedName("employeeName")
    val employeeName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isDeleted")
    val isDeleted: Boolean,
    @SerializedName("lastModificationTime")
    val lastModificationTime: String,
    @SerializedName("lastModifierId")
    val lastModifierId: String,
    @SerializedName("totalDeduction")
    val totalDeduction: Double,
    @SerializedName("recived")
    val recived: Boolean,
    @SerializedName("salaryDetails")
    val salaryDetails: List<SalaryDetail>
) {
    fun getAmount(): String {
        return "$amount IQD"
    }

    fun getTotalDeductionFormatted(): String {
        return "$totalDeduction IQD"
    }

    fun getDateFormatted(): String {

        val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val targetFormat: DateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        val date: Date = originalFormat.parse(dateTime.substring(0, 10))
        return targetFormat.format(date)
    }
}