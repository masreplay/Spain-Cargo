package com.spain_cargo.cargo.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.spain_cargo.cargo.data.db.ItemDb
import com.google.gson.GsonBuilder
import com.spain_cargo.cargo.data.Webservices
import com.spain_cargo.cargo.util.PrefsManager
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
    internal fun provideRetrofit(
        shredPref: SharedPreferences,
        @ApplicationContext context: Context
    ): Webservices {

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
                val token = PrefsManager.instance?.getUser()?.data?.token
                var request = chain.request()
                if (chain.request().header("No-Auth") == null) {
                    request = request.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
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
            .baseUrl(
                "http://phplaravel-547981-1758023.cloudwaysapps.com/"
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build().create(Webservices::class.java)
    }

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): ItemDb {
        return Room.databaseBuilder(context, ItemDb::class.java, "spain_cargo_database").fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun getAppPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}