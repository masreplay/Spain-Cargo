package com.enjaz.university.di.module

import androidx.work.ListenableWorker
import com.enjaz.university.util.ChildWorkerFactory
import com.enjaz.university.util.DataUpdaterWorker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)


@Module
interface WorkerBindingModule {
    @Binds
    @IntoMap
    @WorkerKey(DataUpdaterWorker::class)
    fun bindUpdateWorker(factory: DataUpdaterWorker.Factory): ChildWorkerFactory
}