package com.spain_cargo.cargo.data

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.spain_cargo.cargo.data.db.ItemDb
import com.spain_cargo.cargo.data.model.BaseResource
import com.spain_cargo.cargo.data.model.CreateOrder
import com.spain_cargo.cargo.data.model.Item
import com.spain_cargo.cargo.data.model.brands.BrandsResponse
import com.spain_cargo.cargo.data.model.checkout.CheckoutResponse
import com.spain_cargo.cargo.data.model.cities.CitiesResponse
import com.spain_cargo.cargo.data.model.countries.CountriesResponse
import com.spain_cargo.cargo.data.model.error.ErrorResponse
import com.spain_cargo.cargo.data.model.login.MainResponse
import com.spain_cargo.cargo.data.model.moneyRequests.MoneyRequests
import com.spain_cargo.cargo.data.model.orders.Order
import com.spain_cargo.cargo.data.model.orders.OrderXResponse
import com.spain_cargo.cargo.data.model.orders.OrdersResponse
import com.spain_cargo.cargo.data.model.profile.ProfileResponse
import com.spain_cargo.cargo.data.model.requestMoney.RequestMoneyResponse
import com.spain_cargo.cargo.data.model.requests.MoneyRBResponse
import com.spain_cargo.cargo.data.model.requests.MoneyRBResponseArray
import com.spain_cargo.cargo.data.model.transaction.TransactionResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("CheckResult")
@Singleton
class AppDataManager @Inject constructor(
    private val webservices: Webservices,
    private val database: ItemDb
) {


    fun login(email: String, password: String): Single<BaseResource<MainResponse>> {

        val params = JsonObject().apply {
            addProperty("email", email)
            addProperty("password", password)
        }
        return wrapWithResourceObject(
            webservices.login(jsonElement = params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun logout(): Single<BaseResource<Void>> {
        return wrapWithResourceObject(
            webservices.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun signUp(
        image: MultipartBody.Part, name: String, email: String, password: String,
        phone_number: String, date_of_birth: String
    ): Single<BaseResource<MainResponse>> {


        return wrapWithResourceObject(
            webservices.signUp(
                image = image,
                name = name.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull()),
                email = email.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull()),
                password = password.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull()),
                phone_number = phone_number.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull()),
                date_of_birth = date_of_birth.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull())
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun updateProfile(
        image: MultipartBody.Part? = null, name: String, email: String,
        phone_number: String, date_of_birth: String
    ): Single<BaseResource<ProfileResponse>> {
        return wrapWithResourceObject(
            webservices.updateProfile(
                image = image,
                name = name.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull()),
                email = email.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull()),
                phone_number = phone_number.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull()),
                date_of_birth = date_of_birth.toRequestBody(contentType = "multipart/form-data".toMediaTypeOrNull())
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }


    fun getCountries(): Single<BaseResource<CountriesResponse>> {
        return wrapWithResourceObject(
            webservices.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getBrands(country_id: Int): Single<BaseResource<BrandsResponse>> {
        return wrapWithResourceObject(
            webservices.getBrands(country_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getCities(): Single<BaseResource<CitiesResponse>> {
        return wrapWithResourceObject(
            webservices.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun checkout(order: CreateOrder): Single<BaseResource<CheckoutResponse>> {
        return wrapWithResourceObject(
            webservices.checkout(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getOrders(status: String): Single<BaseResource<OrdersResponse>> {
        return wrapWithResourceObject(
            webservices.getOrders(status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getOrderById(id: String): Single<BaseResource<OrderXResponse>> {
        return wrapWithResourceObject(
            webservices.getOrderById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun deleteOrder(id: String): Single<BaseResource<Order>> {
        return wrapWithResourceObject(
            webservices.deleteOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun markOrderAsComplete(id: String): Single<BaseResource<Order>> {
        return wrapWithResourceObject(
            webservices.markOrderAsComplete(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun markOrderAsRefund(id: String): Single<BaseResource<Order>> {
        return wrapWithResourceObject(
            webservices.markOrderAsRefund(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun moneyRequest(
        from: String,
        amount: Int,
        type: String,
        payment_key: String? = null
    ): Single<BaseResource<RequestMoneyResponse>> {
        return wrapWithResourceObject(
            webservices.moneyRequest(from, amount, type, payment_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }


    fun getMoneyRB(): Single<BaseResource<MoneyRBResponseArray>> {
        return wrapWithResourceObject(
            webservices.getMoneyRB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun moneyBackRequests(
        from: String,
        amount: Int
    ): Single<BaseResource<MoneyRBResponse>> {
        return wrapWithResourceObject(
            webservices.moneyBackRequests(from, amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun markMoneyBrAsRejected(id: Int): Single<BaseResource<String>> {
        return wrapWithResourceObject(
            webservices.markMoneyBrAsRejected(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun markMoneyBrAsAccepted(id: Int): Single<BaseResource<String>> {
        return wrapWithResourceObject(
            webservices.markMoneyBrAsAccepted(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }


    fun getProfile(): Single<BaseResource<ProfileResponse>> {
        return wrapWithResourceObject(
            webservices.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }


    fun getMoneyRequests(page: Int): Single<BaseResource<MoneyRequests>> {
        return wrapWithResourceObject(
            webservices.getMoneyRequests(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun getTransaction(): Single<BaseResource<TransactionResponse>> {
        return wrapWithResourceObject(
            webservices.getTransaction()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun acceptMoneyRequest(id: String): Single<BaseResource<Void>> {
        return wrapWithResourceObject(
            webservices.acceptMoneyRequest(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    fun rejectMoneyRequest(id: String): Single<BaseResource<Void>> {
        return wrapWithResourceObject(
            webservices.rejectMoneyRequest(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }




    fun insertItem(item: Item) {
        database.itemDao().insert(item)
    }

    fun deleteItem(item: Item) {
        database.itemDao().delete(item)
    }


    fun getItems(): Single<List<Item>> {
        return database.itemDao().allCategories
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteItems(): Completable {
        return database.itemDao().nukeTable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
