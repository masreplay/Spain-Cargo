package com.enjaz.hr.data

import android.accounts.AuthenticatorDescription
import com.enjaz.hr.data.db.MovieDB
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.attendance.AttendanceResponse
import com.enjaz.hr.data.model.balance.BalanceResponse
import com.enjaz.hr.data.model.error.ErrorResponse
import com.enjaz.hr.data.model.getLeaveRequests.LeaveRequestResponseItem
import com.enjaz.hr.data.model.home.HomeResponse
import com.enjaz.hr.data.model.home.Teammate
import com.enjaz.hr.data.model.login.LoginResponse
import com.enjaz.hr.data.model.profile.UserInfo
import com.enjaz.hr.data.model.requestsTypes.RequestTypesResponse
import com.enjaz.hr.data.model.salary.SalaryDetailsResponse
import com.enjaz.hr.data.model.sendRequest.SendRequestResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
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

    fun sendLeaveRequest(
        startDate: String,
        endDate: String,
        leaveName: String,
        leaveDescription: String,
        leaveTypeId: Int
    ): Single<BaseResource<SendRequestResponse>> {

        val params = JsonObject().apply {
            addProperty("startDate", startDate)
            addProperty("endDate", endDate)
            addProperty("leaveName", leaveName)
            addProperty("leaveDescription", leaveDescription)
            addProperty("leaveTypeId", leaveTypeId)

        }
        return wrapWithResourceObject(
            webservices.sendLeaveRequest(jsonElement = params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun requestOvertime(
        startDate: String,
        endDate: String
    ): Single<BaseResource<Void>> {

        val params = JsonObject().apply {
            addProperty("startDate", startDate)
            addProperty("endDate", endDate)

        }
        return wrapWithResourceObject(
            webservices.requestOvertime(jsonElement = params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }


    fun sendFingerPrintRequest(description: String,type:Int,time:String): Single<BaseResource<Void>> {
        val params = JsonObject().apply {
            addProperty("description", description)
            addProperty("type", type)
            addProperty("time", time)

        }
        return wrapWithResourceObject(
            webservices.sendFingerPrintRequest(params)
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

    fun getLeaveTypes(timeFlag: Boolean): Single<BaseResource<RequestTypesResponse>> {

        return wrapWithResourceObject(
            webservices.getRequestsTypes(timeFlag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getLeaveBalance(): Single<BaseResource<BalanceResponse>> {

        return wrapWithResourceObject(
            webservices.getLeaveBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getSalaryDetails(): Single<BaseResource<SalaryDetailsResponse>> {

        return wrapWithResourceObject(
            webservices.getSalaryDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getLeaveRequests(
        isManager: Boolean,
        id: Int? = null
    ): Single<BaseResource<ArrayList<LeaveRequestResponseItem>>> {
        return wrapWithResourceObject(
            webservices.getLeaveRequests(isManager, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getProfileInfo(): Single<BaseResource<UserInfo>> {
        return wrapWithResourceObject(
            webservices.getProfileInfo()
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

    fun getMyTeamMates(): Single<BaseResource<List<Teammate>>> {

        return wrapWithResourceObject(
            webservices.getMyTeammates()
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



    fun clearProfilePicture(): Single<BaseResource<String>> {
        return wrapWithResourceObject(
            webservices.clearProfilePicture()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun updateProfilePicture(File: MultipartBody.Part): Single<BaseResource<String>> {
        return wrapWithResourceObject(
            webservices.updateProfilePicture(File)
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
                val errorResponse =
                    gson.fromJson(it.errorBody()?.string(), ErrorResponse::class.java)
                BaseResource.error(errorResponse.error.message, it.body())
            }
        }
    }


}
