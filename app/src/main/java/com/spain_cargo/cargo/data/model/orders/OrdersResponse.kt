package com.spain_cargo.cargo.data.model.orders


import com.google.gson.annotations.SerializedName

data class OrdersResponse(
    @SerializedName("data")
    val data: Data
) {
    companion object {
        // status type
        const val COMPLETED = "completed"
        const val PENDING = "pending"
    }
}