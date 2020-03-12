package com.enjaz.university.data

import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.BaseResponse
import com.enjaz.university.data.model.token.TokenResult
import com.enjaz.university.data.model.video.Category
import com.enjaz.university.data.model.video.VidModel
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

}