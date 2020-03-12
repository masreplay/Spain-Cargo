package com.enjaz.university

import com.enjaz.university.di.MyWorkerFactory
import com.enjaz.university.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class UniversityApp : DaggerApplication() {

    @Inject
    lateinit var factory: MyWorkerFactory

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent
            .builder()
            .application(this)
            .build()


    }

    override fun onCreate() {
        super.onCreate()
//        WorkManager.initialize(this, Configuration.Builder().setWorkerFactory(factory).build())
//        instance = this
//        val config = ImagePipelineConfig.newBuilder(applicationContext)
//            .setResizeAndRotateEnabledForNetwork(true)
//            .setDownsampleEnabled(true)
//            .setBitmapsConfig(Bitmap.Config.RGB_565)
//            .build()
//        Fresco.initialize(this, config)
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // Create the NotificationChannel
//            val name = "myMovieChannel"
//            val descriptionText = ""
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
//            mChannel.description = descriptionText
//
//            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(mChannel)
//        }
//        initWorker()
    }

//    private fun initWorker() {
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//
//        val movieWork =
//            PeriodicWorkRequest.Builder(DataUpdaterWorker::class.java, 5, TimeUnit.HOURS)
//                .setConstraints(constraints)
//                .build()
//
//        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//            MOVIE_WORK,
//            ExistingPeriodicWorkPolicy.KEEP, movieWork
//        )
//    }

    companion object {
        lateinit var instance: UniversityApp
    }
}
