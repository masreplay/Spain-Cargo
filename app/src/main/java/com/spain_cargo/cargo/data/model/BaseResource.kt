package com.spain_cargo.cargo.data.model

data class BaseResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): BaseResource<T> {


            return BaseResource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): BaseResource<T> {
            return BaseResource(Status.ERROR, data, msg)
        }

        fun <T> empty(): BaseResource<T> {
            return BaseResource(Status.EMPTY, null, null)
        }

        fun <T> loading(data: T?): BaseResource<T> {
            return BaseResource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    EMPTY,
    LOADING;
}
