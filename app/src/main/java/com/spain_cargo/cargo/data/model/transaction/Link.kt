package com.spain_cargo.cargo.data.model.transaction


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("label")
    val label: Any?,
    @SerializedName("url")
    val url: Any?
)