package com.spain_cargo.cargo.data

import com.google.gson.JsonElement
import com.spain_cargo.cargo.data.model.CreateOrder
import com.spain_cargo.cargo.data.model.brands.BrandsResponse
import com.spain_cargo.cargo.data.model.cities.CitiesResponse
import com.spain_cargo.cargo.data.model.countries.CountriesResponse
import com.spain_cargo.cargo.data.model.login.MainResponse
import com.spain_cargo.cargo.data.model.orders.OrdersResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface Webservices {

    @POST("api/auth/login")
    fun login(@Body jsonElement: JsonElement): Single<Response<MainResponse>>

    @Multipart
    @POST("api/auth/register")
    fun signUp(
        @Header("Accept") header: String = "application/json",
        @Part image: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("phone_number") phone_number: RequestBody,
        @Part("date_of_birth") date_of_birth: RequestBody
    ): Single<Response<MainResponse>>

    @GET("api/countries")
    fun getCountries(): Single<Response<CountriesResponse>>

    @GET("api/brands/{country_id}")
    fun getBrands(@Path("country_id") country_id: Int): Single<Response<BrandsResponse>>

    @GET("api/cities")
    fun getCities(): Single<Response<CitiesResponse>>

    @POST("api/orders/store")
    fun checkout(@Body order:CreateOrder): Single<Response<String>>

    @GET("api/orders")
    fun getOrders(@Query("status")status:String): Single<Response<OrdersResponse>>

}