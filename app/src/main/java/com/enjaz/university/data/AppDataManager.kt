package com.enjaz.university.data

import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.BaseResponse
import com.enjaz.university.data.model.announcements.AnnouncementResponse
import com.enjaz.university.data.model.grades.GradesResponse
import com.enjaz.university.data.model.schedule.ScheduleResponse
import com.enjaz.university.data.model.token.TokenResult
import com.enjaz.university.data.model.video.VidModel
import com.google.gson.JsonObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val webservices: Webservices
) {
    val token: String =
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjQiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiU3R1ZGVudC0xIiwiQXNwTmV0LklkZW50aXR5LlNlY3VyaXR5U3RhbXAiOiJiZjM4Y2JiZS0yOTA2LTdkMDctMGFkMy0zOWY0YjA2MTljNDAiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJTdHVkZW50IiwiaHR0cDovL3d3dy5hc3BuZXRib2lsZXJwbGF0ZS5jb20vaWRlbnRpdHkvY2xhaW1zL3RlbmFudElkIjoiMiIsInN1YiI6IjQiLCJqdGkiOiI1NjIzNTY2NS05YmI2LTQ5MmUtOTkxNC1mZDVkMDk3OTBkMDIiLCJpYXQiOjE1ODc1NzAxMDEsInRva2VuX3ZhbGlkaXR5X2tleSI6ImM5YjcxNjRiLWU3ODMtNDU1ZS1iMGE2LTdlNmNjMTdjNDQ2YyIsInVzZXJfaWRlbnRpZmllciI6IjRAMiIsInRva2VuX3R5cGUiOiIwIiwibmJmIjoxNTg3NTcwMTAxLCJleHAiOjE1ODc2NTY1MDEsImlzcyI6IlVJUyIsImF1ZCI6IlVJUyJ9.2bWBH8tGEq6rBmkd06y1JCVBmy4D9LMnxG8a-FoFnhQ"
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


    fun getSchedule(filter: String): Single<BaseResource<BaseResponse<ScheduleResponse>>> {

        return wrapWithResourceObject(
            webservices.getScheddule(filter, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )

    }

    fun getAnnouncements(filter: String): Single<BaseResource<BaseResponse<AnnouncementResponse>>> {

        return wrapWithResourceObject(
            webservices.getAnnouncements(filter, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )

    }

    fun getGrades(): Single<BaseResource<BaseResponse<MutableList<GradesResponse>>>> {

        return wrapWithResourceObject(
            webservices.getGrades(2, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )

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