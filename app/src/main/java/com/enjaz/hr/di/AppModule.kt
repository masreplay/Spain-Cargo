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
import retrofit2.converter.scalars.ScalarsConverterFactory
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
                        .addHeader("Authorization", "Bearer $token")
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
            .baseUrl("http://10.10.1.152:5001/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
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