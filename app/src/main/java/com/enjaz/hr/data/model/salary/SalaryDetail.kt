package com.enjaz.hr.data.model.salary


import com.google.gson.annotations.SerializedName

data class SalaryDetail(
    @SerializedName("allocationName")
    val allocationName: String,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("historyId")
    val historyId: Int,
    @SerializedName("historyName")
    val historyName: String,
    @SerializedName("isAddition")
    val isAddition: Boolean,
    @SerializedName("salaryId")
    val salaryId: Int,
    @SerializedName("variableId")
    val variableId: Int
)