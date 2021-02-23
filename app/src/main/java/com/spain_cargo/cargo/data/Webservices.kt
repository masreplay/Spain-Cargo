package com.spain_cargo.cargo.data

import com.google.gson.JsonElement
import com.spain_cargo.cargo.data.model.CreateOrder
import com.spain_cargo.cargo.data.model.brands.BrandsResponse
import com.spain_cargo.cargo.data.model.cities.CitiesResponse
import com.spain_cargo.cargo.data.model.countries.CountriesResponse
import com.spain_cargo.cargo.data.model.login.MainResponse
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.data.model.orders.OrdersResponse
import com.spain_cargo.cargo.data.model.profile.ProfileResponse
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
    fun checkout(@Body order: CreateOrder): Single<Response<String>>

    @GET("api/user")
    fun getUser(): Single<Response<ProfileResponse>>

    // Order
    @GET("api/orders")
    fun getOrders(@Query("status") status: String): Single<Response<OrdersResponse>>

    @GET("api/orders/{id}")
    fun getOrderById(@Path("id") id: String): Single<Response<Order>>

    @DELETE("api/orders/{id}")
    fun deleteOrder(@Path("id") id: String): Single<Response<Order>>

    @PATCH("api/orders/complete/{id}")
    fun markOrderAsComplete(@Path("id") id: String): Single<Response<Order>>

    @PATCH("api/orders/refund/{id}")
    fun markOrderAsRefund(@Path("id") id: String): Single<Response<Order>>

    @FormUrlEncoded
    @POST("api/money-requests/store")
    fun moneyRequest(
        @Field("from") from: String,
        @Field("amount") amount: Int,
        @Field("type") type: String,
        @Field("payment_key") payment_key: String?
    ): Single<Response<RequestMoneyResponse>>

    @GET("api/money-requests")
    fun getMoneyRequests(): Single<Response<MoneyRequests>>

    @PATCH("api/money-requests/accept/{id}")
    fun acceptMoneyRequest(@Path("id")id:String): Single<Response<Void>>

    @PATCH("api/money-requests/reject/{id}")
    fun rejectMoneyRequest(@Path("id")id:String): Single<Response<Void>>

    // Money request back
    @GET("api/money-back-requests")
    fun getMoneyRB(): Single<Response<MoneyRBResponseArray>>

    @FormUrlEncoded
    @POST("api/money-back-requests/store")
    fun moneyBackRequests(
        @Field("from_request") from: String,
        @Field("amount") amount: Int
    ): Single<Response<MoneyRBResponse>>

    @PATCH("api/money-back-requests/accept/{id}")
    fun markMoneyBrAsAccepted(@Path("id") id: Int): Single<Response<String>>

    @PATCH("/api/money-back-requests/reject/{id}")
    fun markMoneyBrAsRejected(@Path("id") id: Int): Single<Response<String>>

}