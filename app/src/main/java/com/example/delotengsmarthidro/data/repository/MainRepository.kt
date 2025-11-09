package com.example.delotengsmarthidro.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.delotengsmarthidro.data.database.HistoryDao
import com.example.delotengsmarthidro.data.database.HistoryDatabase
import com.example.delotengsmarthidro.data.database.HistoryEntity
import com.example.delotengsmarthidro.data.remote.ApiService
import com.example.delotengsmarthidro.data.response.WeatherResponse
import com.example.delotengsmarthidro.utils.await
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainRepository private constructor(
    private val apiService: ApiService,
    private val context: Context
){
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }
//    private val executorsService: ExecutorService = Executors.newSingleThreadExecutor()
//    private val historyDao: HistoryDao
//
//    fun insert(history: HistoryEntity){
//        executorsService.execute { historyDao.insert(history) }
//    }
//
//    fun delete(history: HistoryEntity){
//        executorsService.execute { historyDao.delete(history)}
//    }

    suspend fun getWeatherData(): WeatherResponse {
        if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            throw SecurityException("Izin Lokasi Tidak diberikan")
        }
        val location = fusedLocationClient.lastLocation.await()
        location?.let {
            val latitude = it.latitude
            val longitude = it.longitude

            return apiService.getWeatherData(
                lat = latitude,
                lon = longitude,
                appid = "2f674dcbcefdf41ce188aa40d1b70278",
            )
        } ?: throw Exception("Lokasi tidak ditemukan")
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(apiService: ApiService, context: Context) =
            instance ?: synchronized(this) {
                instance ?: MainRepository(apiService, context)
            }.also { instance = it }
    }
}