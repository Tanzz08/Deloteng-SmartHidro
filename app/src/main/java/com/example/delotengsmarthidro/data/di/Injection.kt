package com.example.delotengsmarthidro.data.di

import android.content.Context
import com.example.delotengsmarthidro.data.database.TutorDatabase
import com.example.delotengsmarthidro.data.remote.ApiConfig
import com.example.delotengsmarthidro.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

object Injection {
    private val applicationScope = CoroutineScope(SupervisorJob())

    fun provideMainRepository(context: Context): MainRepository {
        // 1. Ambil ApiService (sudah benar)
        val apiService = ApiConfig.getWeather()

        // 2. Ambil Database (perlu context dan scope)
        val database = TutorDatabase.getTutorDatabase(context.applicationContext, applicationScope)

        // 3. Ambil Dao dari database
        val tutorDao = database.tutorDao()

        // 4. Berikan SEMUA 3 parameter ke getInstance
        // (Pastikan urutannya benar sesuai file MainRepository.kt Anda)
        return MainRepository.getInstance(apiService, context.applicationContext, tutorDao)
    }
}