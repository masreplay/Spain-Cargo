package com.enjaz.hr.data

import com.enjaz.hr.data.db.MovieDB
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.Error
import com.enjaz.hr.data.model.attendance.AttendanceResponse
import com.enjaz.hr.data.model.getLeaveRequests.LeaveRequestResponse
import com.enjaz.hr.data.model.home.HomeResponse
import com.enjaz.hr.data.model.login.LoginResponse
import com.enjaz.hr.data.model.requestsTypes.RequestTypesResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val webservices: Webservices,
    private val database: MovieDB
) {


    fun getAttendance(month: Int?, year: Int?): Single<BaseResource<AttendanceResponse>> {

        val params = JsonObject().apply {
            addProperty("month", month)
            addProperty("year", year)
        }
        return wrapWithResourceObject(
            webservices.getAttendanceResponse(jsonElement = params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }


    fun cancelMyRequest(workCorrelationId: String, newStatus: Int): Single<BaseResource<String>> {

        return wrapWithResourceObject(
            webservices.cancelMyRequest(workCorrelationId, newStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getLeaveTypes(timeFlag:Boolean): Single<BaseResource<RequestTypesResponse>> {

        return wrapWithResourceObject(
            webservices.getRequestsTypes(timeFlag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getLeaveRequests(
        boolean: Boolean,
        id: Int? = null
    ): Single<BaseResource<LeaveRequestResponse>> {

        return wrapWithResourceObject(
            webservices.getLeaveRequests(boolean, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getHome(month: Int?, year: Int?): Single<BaseResource<HomeResponse>> {

        val params = JsonObject().apply {
            addProperty("month", month)
            addProperty("year", year)
        }
        return wrapWithResourceObject(
            webservices.getHomeResponse(jsonElement = params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun login(username: String, pass: String): Single<BaseResource<LoginResponse>> {

        return wrapWithResourceObject(
            webservices.login("EnjazERP_App", "password", username, pass, "EnjazERP")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }


    private fun <T> wrapWithResourceObject(response: Single<Response<T>>): Single<BaseResource<T>> {
        return response.map {
            if (it.code() in 200 until 300) {
                BaseResource.success(it.body())


            } else {
                val gson = Gson()
                val errorResponse = gson.fromJson(it.errorBody()?.string(), Error::class.java)
                BaseResource.error(errorResponse.errorDescription, it.body())
            }
        }
    }


}
