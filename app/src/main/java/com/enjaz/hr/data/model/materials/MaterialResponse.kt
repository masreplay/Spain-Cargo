package com.enjaz.hr.data.model.materials


import com.enjaz.hr.data.model.materials.Item
import com.google.gson.annotations.SerializedName

data class MaterialResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("totalCount")
    val totalCount: Int
)