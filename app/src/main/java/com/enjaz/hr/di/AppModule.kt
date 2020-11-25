package com.enjaz.hr.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.enjaz.hr.data.Webservices
import com.enjaz.hr.data.db.MovieDB
import com.enjaz.hr.util.PrefsManager
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    internal fun provideRetrofit(shredPref: SharedPreferences,@ApplicationContext context: Context): Webservices {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()


        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .build()
                return@addInterceptor chain.proceed(newRequest)
            }

            .addInterceptor { chain ->
                val token = PrefsManager.instance?.getAccessToken()
                var request = chain.request()
                if (chain.request().header("No-Auth") == null) {
                    request = request.newBuilder()
                        .addHeader("Authorization", "Bearer "+"eyJhbGciOiJSUzI1NiIsImtpZCI6IkhkSDdaeWRadjZjQjZVTUJUdUpJZVEiLCJ0eXAiOiJhdCtqd3QifQ.eyJuYmYiOjE2MDYxNzM2MzgsImV4cCI6MTYzNzcwOTYzOCwiaXNzIjoiaHR0cDovLzEwLjEwLjEuMTUyOjUwMDEiLCJhdWQiOiJFbmphekVSUCIsImNsaWVudF9pZCI6IkVuamF6RVJQX0FwcCIsInN1YiI6IjM5ZjhlMzVlLTg4ZjctNjQxOS0xMmNjLTI5NjNkMzBmNjU2YyIsImF1dGhfdGltZSI6MTYwNjE3MzYzNywiaWRwIjoibG9jYWwiLCJuYW1lIjoiaGFpdGhhbSIsImVtYWlsIjoiaC5iLmguaXJzQG91dGxvb2suY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJzY29wZSI6WyJFbmphekVSUCJdLCJhbXIiOlsicHdkIl19.kiWERPBfjukvxbHGv2h0hnfL-Fd3cTTPfbJDwYR44P9I_MCiD7bzc8Mki-BmZfoNRD0c7IgTyEYzBSa3ZkInADvLgyP0lpwBxOzzbT_up2FvAIp5b5dj-29GygnePIE8ENxtVs0s9_Kl1QmuoVDRVzpSy7_V3NXBo8FJtkbcyBYkulV8UVzT7EimgpFsaENzdRmE3r56Jciycn8F1gwnKdDXmQsb1AabYgsydlxkTdJ8FwK9wBpCdDPw2EXDmXy0nZpeOIYiGNsaaa_9pIbOsdWc61yiuqTsE7E6Ss0RTsgu-4_rk48AcEIiFvsF-cDLud5P-ooR2f5Aar1vR4wPuQ")
                        .build()

                }
                return@addInterceptor chain.proceed(request)
            }


            .addInterceptor(logging)

            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()




        return Retrofit.Builder()
            .baseUrl("http://10.10.1.152:5001/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build().create(Webservices::class.java)
    }


    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): MovieDB {
        return Room.databaseBuilder(context, MovieDB::class.java, "hr_database").build()

    }

    @Singleton
    @Provides
    fun getAppPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}