package com.enjaz.hr.data.model.salary


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("creationTime")
    val creationTime: String,
    @SerializedName("creatorId")
    val creatorId: String,
    @SerializedName("dateTime")
    val dateTime: String,
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
    @SerializedName("recived")
    val recived: Boolean,
    @SerializedName("salaryDetails")
    val salaryDetails: List<SalaryDetail>
)