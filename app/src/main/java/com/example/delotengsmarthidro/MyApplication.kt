package com.example.delotengsmarthidro

import android.app.Application
import com.example.delotengsmarthidro.data.database.TutorDatabase
import com.example.delotengsmarthidro.data.remote.ApiConfig
import com.example.delotengsmarthidro.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

// MyApplication.kt

class MyApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { TutorDatabase.getTutorDatabase(this, applicationScope) }
    val apiService by lazy { ApiConfig.getWeather() }

    val repository by lazy {
        MainRepository.getInstance(apiService, this, database.tutorDao())
    }
}