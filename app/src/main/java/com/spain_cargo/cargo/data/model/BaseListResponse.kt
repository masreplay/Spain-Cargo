package com.spain_cargo.cargo.data.model

import com.google.gson.annotations.SerializedName


data class BaseListResponse<T>(
    @SerializedName("items") val items: List<T>,
    @SerializedName("totalCount") val totalCount: Int
)