package com.example.delotengsmarthidro.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import com.example.delotengsmarthidro.data.database.HistoryDao
import com.example.delotengsmarthidro.data.database.HistoryEntity
import com.example.delotengsmarthidro.data.remote.ApiService
import com.example.delotengsmarthidro.data.response.WeatherResponse
import com.example.delotengsmarthidro.utils.await
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainRepository private constructor(
    private val apiService: ApiService,
    private val historyDao: HistoryDao,
    val context: Context
){
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }
    private val executorsService: ExecutorService = Executors.newSingleThreadExecutor()

    suspend fun insert(history: HistoryEntity){
        executorsService.execute { historyDao.insert(history) }
    }

    suspend fun delete(history: HistoryEntity){
        executorsService.execute { historyDao.delete(history)}
    }

    fun getAllHistory(): LiveData<List<HistoryEntity>> = historyDao.getAllHistory()

    suspend fun getWeatherData(): WeatherResponse {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            throw SecurityException("Izin Lokasi Tidak diberikan")
        }

        val cancellationTokenSource = CancellationTokenSource()
        val location = fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource.token
        ).await()
        location?.let {
            val latitude = it.latitude
            val longitude = it.longitude
            Log.d("MainRepository", "Akurasi Lokasi: ${it.accuracy} meter")
            Log.d("MainRepository", "Latitude: $latitude, Longitude: $longitude")

            return apiService.getWeatherData(
                lat = latitude,
                lon = longitude,
                appid = "a1d91f746849c525b26c14cf44cd7a6f",
            )
        } ?: throw Exception("Lokasi tidak ditemukan")
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(apiService: ApiService, context: Context, historyDao: HistoryDao) =
            instance ?: synchronized(this) {
                instance ?: MainRepository(apiService, historyDao, context)
            }.also { instance = it }
    }
}