package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
)