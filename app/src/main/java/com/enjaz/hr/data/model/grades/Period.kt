package com.enjaz.hr.data.model.grades

import com.google.gson.annotations.SerializedName

data class Period(
    @SerializedName("grades")
    val grades: MutableList<Grade>,
    @SerializedName("periodName")
    val periodName: String,

    var expanded: Boolean = false,
    var parent: Boolean = false

)


