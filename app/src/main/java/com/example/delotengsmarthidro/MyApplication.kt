package com.example.delotengsmarthidro

import android.app.Application
import com.example.delotengsmarthidro.data.database.HistoryDatabase
import com.example.delotengsmarthidro.data.di.Injection
import com.example.delotengsmarthidro.data.remote.ApiConfig
import com.example.delotengsmarthidro.data.repository.MainRepository

// MyApplication.kt

class MyApplication : Application() {
    val historyDatabase by lazy { HistoryDatabase.getDatabase(this, Injection.applicationScope) }
    val apiService by lazy { ApiConfig.getWeather() }

    val repository by lazy {
        MainRepository.getInstance(
            apiService,
            this,
            // Ganti dengan historyDao
            historyDatabase.historyDao()
        )
    }
}