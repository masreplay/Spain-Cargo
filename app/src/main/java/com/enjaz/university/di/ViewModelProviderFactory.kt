package com.enjaz.university.di

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enjaz.university.data.AppDataManager
import com.enjaz.university.data.db.MovieDB
import com.enjaz.university.ui.home.HomeViewModel
import com.enjaz.university.ui.login.LoginViewModel
import com.enjaz.university.ui.schedual.ScheduleViewModel

import com.enjaz.university.util.bundleToMap
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelProviderFactory @Inject
constructor(private val dataManager: AppDataManager, private val dataBase: MovieDB) : ViewModelProvider.NewInstanceFactory() {
    private val viewArguments = mutableMapOf<Class<*>, Map<String, Any?>>()
//
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(dataManager) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(dataManager) as T
            modelClass.isAssignableFrom(ScheduleViewModel::class.java) -> ScheduleViewModel(dataManager) as T
//            modelClass.isAssignableFrom(VerticalViewModel::class.java) -> VerticalViewModel(dataManager,dataBase,viewArguments[modelClass] as Map<String, Any>?) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
//
    fun <V> seedArguments(viewModelClass: Class<V>, arguments: Bundle?) {
        viewArguments[viewModelClass] = bundleToMap(arguments)
    }
}