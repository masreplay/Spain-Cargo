package com.enjaz.hr.data.model.homework


import com.enjaz.hr.data.model.homework.Attachment
import com.enjaz.hr.data.model.homework.Homework
import com.enjaz.hr.data.model.homework.HomewrokAnswer
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("attachments")
    val attachments: List<Attachment>,
    @SerializedName("homework")
    val homework: Homework,
    @SerializedName("periodName")
    val periodName: String,
    @SerializedName("subLectureName")
    val subLectureName: String,
    @SerializedName("profName")
    val profName: String,
    @SerializedName("lectureHomework")
    val lectureHomework: String,
    @SerializedName("homeworkAnswer")
    val homeworkAnswer: HomewrokAnswer


)