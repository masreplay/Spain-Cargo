package com.enjaz.hr.data.model.materials


import com.google.gson.annotations.SerializedName

data class Material(
    @SerializedName("description")
    val description: String,
    @SerializedName("educationLevel")
    val educationLevel: Int,
    @SerializedName("fieldId")
    val fieldId: Int,
    @SerializedName("finalWeight")
    val finalWeight: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("midtermWeight")
    val midtermWeight: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("programId")
    val programId: Int,
    @SerializedName("requiredFrom")
    val requiredFrom: Any,
    @SerializedName("requiredOrNot")
    val requiredOrNot: Boolean,
    @SerializedName("studyingHours")
    val studyingHours: Int,
    @SerializedName("units")
    val units: Int
)