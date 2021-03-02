package com.spain_cargo.cargo.data.model.transaction


import com.google.gson.annotations.SerializedName

data class Transactions(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<Transaction>,
    @SerializedName("first_page_url")
    val firstPageUrl: String,
    @SerializedName("from")
    val from: Any?,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("last_page_url")
    val lastPageUrl: String,
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("next_page_url")
    val nextPageUrl: Any?,
    @SerializedName("path")
    val path: String,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("prev_page_url")
    val prevPageUrl: Any?,
    @SerializedName("to")
    val to: Any?,
    @SerializedName("total")
    val total: Int
)