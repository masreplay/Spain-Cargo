package com.enjaz.hr.data

import com.enjaz.hr.data.db.MovieDB
import com.enjaz.hr.data.model.BaseResource
import com.enjaz.hr.data.model.Error
import com.enjaz.hr.data.model.home.HomeResponse
import com.enjaz.hr.data.model.login.LoginResponse
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



    fun getHome(month:Int? , year:Int?):Single<BaseResource<HomeResponse>>{

        val params = JsonObject().apply {
            addProperty("month",month)
            addProperty("year",year)
        }
        return wrapWithResourceObject(webservices.getHomeResponse(jsonElement = params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()))
    }

    fun login(username:String , pass:String):Single<BaseResource<LoginResponse>>{

        return wrapWithResourceObject(webservices.login("EnjazERP_App","password",username,pass,"EnjazERP")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()))
    }


    private fun <T> wrapWithResourceObject(response: Single<Response<T>>): Single<BaseResource<T>> {
        return response.map {
            if (it.code() != 200) {
                val gson = Gson()
                val errorResponse = gson.fromJson(it.errorBody()?.string(), Error::class.java)
                BaseResource.error(errorResponse.errorDescription, it.body())
            }
            else {
                BaseResource.success(it.body())
            }
        }
    }


}
