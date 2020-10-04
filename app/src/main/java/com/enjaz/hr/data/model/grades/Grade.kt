package com.enjaz.hr.data.model.grades

import com.google.gson.annotations.SerializedName

data class Grade(
    @SerializedName("degree")
    val degree: Int,
    @SerializedName("degreeFrom")
    val degreeFrom: Int,
    @SerializedName("examDate")
    val examDate: String,
    @SerializedName("examId")
    val examId: Int,
    @SerializedName("examName")
    val examName: String,
    @SerializedName("gradeType")
    val gradeType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("periodId")
    val periodId: Int,
    @SerializedName("studentName")
    val studentName: Any,
    @SerializedName("subLectureName")
    val subLectureName: String,
    @SerializedName("subLectureType")
    val subLectureType: String,
    @SerializedName("subLectureProf")
    val subLectureProf: String,
    @SerializedName("yearName")
    val yearName: String


)