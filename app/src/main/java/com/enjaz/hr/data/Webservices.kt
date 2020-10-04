package com.enjaz.hr.data

import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.announcements.AnnouncementResponse
import com.enjaz.hr.data.model.grades.GradesResponse
import com.enjaz.hr.data.model.schedule.ScheduleResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.data.model.video.Category
import com.enjaz.hr.data.model.video.VidModel
import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface Webservices {


    @GET("people")
    fun searchPeople(@Query("search") searchKeyWord: String): Single<Response<Object>>


    @GET("movies/{type}")
    fun getMovies(
        @Path("type") type: String,
        @Query("genres") genres: String?,
        @Query("extended") full: String?
    ): Single<Response<List<VidModel>>>

    @GET("genres/movies")
    fun getCategory(): Single<Response<List<Category>>>


    @POST("TokenAuth/Authenticate")
    fun login(
        @Body jsonElement: JsonElement
    ): Single<Response<BaseResponse<TokenResult>>>


    @GET("/api/services/app/Schedules/GetAll")
    fun getScheddule(
        @Query("DayFilter") filter: String,
        @Header("Authorization") token: String
    ): Single<Response<BaseResponse<ScheduleResponse>>>


    @GET("/api/services/app/UniversityEvents/GetAll")
    fun getAnnouncements(
        @Query("NameFilter") filter: String,
        @Header("Authorization") token: String
    ): Single<Response<BaseResponse<AnnouncementResponse>>>


    @GET("/api/services/app/Grades/GetAllOfUser")
    fun getGrades(
        @Query("status") filter: Int,
        @Header("Authorization") token: String
    ): Single<Response<BaseResponse<MutableList<GradesResponse>>>>


}