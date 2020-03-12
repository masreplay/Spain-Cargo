package com.enjaz.university.data

import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.BaseResponse
import com.enjaz.university.data.model.token.TokenResult
import com.enjaz.university.data.model.video.Category
import com.enjaz.university.data.model.video.VidModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val webservices: Webservices
) {
    fun getMovies(type:String , genre:String?= ""): Single<BaseResource<List<VidModel>>> {
        return wrapWithResourceObject(webservices.getMovies(type,genre,"full")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()))
            .map { r->
                r.data?.forEach {
                    it.movie?.movieId = it.movie?.ids?.trakt?:-1
                    it.movie?.movieType=type
                }
                BaseResource.success(r.data)
            }
                }

    fun login(username:String , pass:String):Single<BaseResource<BaseResponse<TokenResult>>>{

        val params = JsonObject().apply {
            addProperty("userNameOrEmailAddress",username)
            addProperty("password",pass)
        }
        return wrapWithResourceObject(webservices.login(jsonElement = params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()))
    }


    private fun <T> wrapWithResourceObject(response: Single<Response<T>>): Single<BaseResource<T>> {
        return response.map {
            if (it.code() != 200) BaseResource.error(it.errorBody()?.string()?:"", it.body())
            else {
                BaseResource.success(it.body())
            }
        }
    }

}
