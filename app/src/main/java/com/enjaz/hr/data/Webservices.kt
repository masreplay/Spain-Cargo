package com.enjaz.hr.data

import com.enjaz.hr.data.model.login.LoginResponse
import com.enjaz.hr.data.model.home.HomeResponse
import com.enjaz.hr.data.model.token.TokenResult
import com.enjaz.hr.data.model.video.Category
import com.enjaz.hr.data.model.video.VidModel
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




    @POST("hr/Employee/GetMobileHomeStatistics")
    fun getHomeResponse(@Body jsonElement: JsonElement): Single<Response<HomeResponse>>
    @FormUrlEncoded
    @POST("connect/token")
    fun login(@Field ("client_id")client_id:String,@Field ("grant_type")grant_type:String,
              @Field ("username")username:String,@Field ("password")password:String,@Field ("Scope")Scope:String):
            Single<Response<LoginResponse>>





}