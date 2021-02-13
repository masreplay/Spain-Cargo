package com.enjaz.hr.data.model.home


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Employee(
    @SerializedName("departmentName")
    val departmentName: String?,
    @SerializedName("employeeNo")
    val employeeNo: Any?,
    @SerializedName("file")
    val `file`: Any?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("pictureUrl")
    val pictureUrl: String?
) : Serializable