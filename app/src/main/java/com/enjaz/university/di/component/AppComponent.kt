package com.enjaz.university.di.component

import android.app.Application
import com.enjaz.university.UniversityApp
import com.enjaz.university.di.module.ActivityBuilder
import com.enjaz.university.di.module.AppModule
import com.enjaz.university.di.module.WorkerBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class
        , AppModule::class
        , WorkerBindingModule::class,
        ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<UniversityApp> {

    override fun inject(app: UniversityApp)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}