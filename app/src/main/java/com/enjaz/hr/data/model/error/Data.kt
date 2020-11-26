package com.enjaz.hr.data.model.error


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("additionalProp1")
    val additionalProp1: AdditionalProp1,
    @SerializedName("additionalProp2")
    val additionalProp2: AdditionalProp2,
    @SerializedName("additionalProp3")
    val additionalProp3: AdditionalProp3
)