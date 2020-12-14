package com.enjaz.hr.data

import com.enjaz.hr.data.model.attendance.AttendanceResponse
import com.enjaz.hr.data.model.balance.BalanceResponse
import com.enjaz.hr.data.model.getLeaveRequests.LeaveRequestResponse
import com.enjaz.hr.data.model.home.HomeResponse
import com.enjaz.hr.data.model.login.LoginResponse
import com.enjaz.hr.data.model.profile.UserInfo
import com.enjaz.hr.data.model.requestsTypes.RequestTypesResponse
import com.enjaz.hr.data.model.salary.SalaryDetailsResponse
import com.enjaz.hr.data.model.sendRequest.SendRequestResponse
import com.enjaz.hr.data.model.video.Category
import com.enjaz.hr.data.model.video.VidModel
import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface Webservices {


    @GET("movies/{type}")
    fun getMovies(
        @Path("type") type: String,
        @Query("genres") genres: String?,
        @Query("extended") full: String?
    ): Single<Response<List<VidModel>>>

    @GET("genres/movies")
    fun getCategory(): Single<Response<List<Category>>>


    @GET("api/hr/md/LeavesType/GetList")
    fun getRequestsTypes(@Query("TimeFlag")timeFlag :Boolean): Single<Response<RequestTypesResponse>>


    @PUT("api/hr/md/Leaves/ChangeLeaveRequest")
    fun cancelMyRequest(@Query("WorkflowCorrelationId")workflowCorrelationId:String,@Query("NewStatus")newStatus:Int): Single<Response<String>>


    @POST("api/hr/Attendance/GetMobileAttendanceStatistics")
    fun getAttendanceResponse(@Body jsonElement: JsonElement): Single<Response<AttendanceResponse>>

    @GET("api/hr/md/Leaves/GetLeavesRequests")
    fun getLeaveRequests(@Query("AsManger")boolean: Boolean,@Query("leaveStatus")id:Int?=null): Single<Response<LeaveRequestResponse>>

    @POST("api/hr/Employee/GetMobileHomeStatistics")
    fun getHomeResponse(@Body jsonElement: JsonElement): Single<Response<HomeResponse>>

    @POST("api/hr/Employee/GetMyProfileInfo")
    fun getProfileInfo(): Single<Response<UserInfo>>

    @POST("api/hr/md/Leaves/RequestLeave")
    fun sendLeaveRequest(@Body jsonElement: JsonElement): Single<Response<SendRequestResponse>>

    @POST("api/hr/md/LeaveBalance/GetMyLeaveBalances")
    fun getLeaveBalance(): Single<Response<BalanceResponse>>


    @GET("api/payroll/md/Salary/GetSalaryListForEmployee")
    fun getSalaryDetails(): Single<Response<SalaryDetailsResponse>>


    @FormUrlEncoded
    @POST("connect/token")
    fun login(@Field ("client_id")client_id:String,@Field ("grant_type")grant_type:String,
              @Field ("username")username:String,@Field ("password")password:String,@Field ("Scope")Scope:String):
            Single<Response<LoginResponse>>

}