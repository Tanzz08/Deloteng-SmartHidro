package com.example.delotengsmarthidro.data.di

import android.content.Context
import com.example.delotengsmarthidro.data.database.HistoryDatabase
import com.example.delotengsmarthidro.data.remote.ApiConfig
import com.example.delotengsmarthidro.data.repository.MainRepository
import com.example.delotengsmarthidro.preference.Preference
import com.example.delotengsmarthidro.preference.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

object Injection {
    val applicationScope = CoroutineScope(SupervisorJob())

    fun provideMainRepository(context: Context): MainRepository {
        val apiService = ApiConfig.getWeather()
        val database = HistoryDatabase.getDatabase(context.applicationContext, applicationScope)
        val historyDao = database.historyDao()
        return MainRepository.getInstance(apiService, context.applicationContext, historyDao)
    }

    fun userPreference(context: Context): Preference {
        return Preference.getInstance(context.dataStore)
    }
}