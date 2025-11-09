package com.example.delotengsmarthidro.data.di

import android.content.Context
import com.example.delotengsmarthidro.data.remote.ApiConfig
import com.example.delotengsmarthidro.data.repository.MainRepository

object Injection {
    fun provideMainRepository(context: Context): MainRepository {
        val apiService = ApiConfig.getWeather()
        return MainRepository.getInstance(apiService, context)
    }
}