
package com.enjaz.university.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.data.db.MovieDB
import com.enjaz.university.data.model.BaseResource
import com.enjaz.university.data.model.video.Category
import com.enjaz.university.data.model.video.VidModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject


class DataUpdaterWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val movieDB: MovieDB,
    private val dataManager: AppDataManager
) : Worker(appContext, params) {
    @SuppressLint("CheckResult")
    override fun doWork(): Result {

//        return try {
//            dataManager.getMovies(TRENDING).subscribe({ onMoviesResponse(it) }, { onErrorReceived(it) })
//            dataManager.getMovies(BOX_OFFICE).subscribe({ onMoviesResponse(it) }, { onErrorReceived(it) })
//            dataManager.getCategory().subscribe({ onCategoryResponse(it) }, { onErrorReceived(it) })
//
//            Result.success()
//        } catch (exception: Exception) {
//            Log.e(TAG, "Unable to execute work", exception)
//            Result.failure()
//        }
        return Result.success()
    }

    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory

    companion object {
        private val TAG = DataUpdaterWorker::class.java.simpleName
    }

    private fun onMoviesResponse(result: BaseResource<List<VidModel>>) {
        Log.d(TAG, "worker response" + result.data?.size)
        val movies=result.data?.mapNotNull { it.movie }
        callInBackground(Runnable {movies?.let {  movieDB.listsDao()?.insertList(it)}})
    }

    private fun onCategoryResponse(result: BaseResource<List<Category>>) {
        callInBackground(Runnable {result.data?.let {  movieDB.categoryDao()?.insertList(it)}})
    }
    private fun onErrorReceived(error: Throwable) {
        Log.e(TAG, "worker response error "+error.message)
    }
}

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
}