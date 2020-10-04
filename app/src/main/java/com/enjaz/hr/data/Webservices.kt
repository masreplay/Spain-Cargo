package com.enjaz.hr.data

import com.enjaz.hr.data.model.BaseResponse
import com.enjaz.hr.data.model.token.TokenResult
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


    @POST("TokenAuth/Authenticate")
    fun login(
        @Body jsonElement: JsonElement
    ): Single<Response<BaseResponse<TokenResult>>>


    /*@GET("/api/services/app/Schedules/GetAll")
    fun getScheddule(
        @Query("DayFilter") filter: String,
        @Header("Authorization") token: String
    ): Single<Response<BaseResponse<ScheduleResponse>>>*/


}