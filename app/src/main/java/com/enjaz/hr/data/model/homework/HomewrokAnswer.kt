package com.enjaz.hr.data.model.homework

import com.google.gson.annotations.SerializedName

data class HomewrokAnswer(

    @SerializedName("attachments")
    val attachments: List<Attachment>,
    @SerializedName("homework")
    val homework: Homework,
    @SerializedName("periodName")
    val periodName: String,
    @SerializedName("subLectureName")
    val subLectureName: String,
    @SerializedName("profName")
    val profName: String


)