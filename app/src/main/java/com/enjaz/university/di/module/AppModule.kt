package com.enjaz.university.di.module

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.enjaz.university.BuildConfig
import com.enjaz.university.data.Webservices
import com.enjaz.university.data.db.MovieDB
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [AssistedInject_AppModule::class])
@AssistedModule

class AppModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Webservices {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .build()
                return@addInterceptor chain.proceed(newRequest)
            }
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build().create(Webservices::class.java)
    }
    @Provides
    @Singleton
    fun providesContext(app: Application): Context = app

    @Provides
    @Singleton
    fun providesRoomDatabase(  context: Context): MovieDB {
        return  Room.databaseBuilder(context, MovieDB::class.java, "movie_database").build()

    }
}