package com.enjaz.hr.data.model.requestsTypes


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("creationTime")
    val creationTime: String,
    @SerializedName("creatorId")
    val creatorId: String,
    @SerializedName("deleterId")
    val deleterId: Any,
    @SerializedName("deletionTime")
    val deletionTime: Any,
    @SerializedName("effectBalanceFlag")
    val effectBalanceFlag: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isDeleted")
    val isDeleted: Boolean,
    @SerializedName("lastModificationTime")
    val lastModificationTime: String,
    @SerializedName("lastModifierId")
    val lastModifierId: String,
    @SerializedName("maxDuration")
    val maxDuration: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("paidFlag")
    val paidFlag: Boolean,
    @SerializedName("timeFlag")
    val timeFlag: Boolean
)