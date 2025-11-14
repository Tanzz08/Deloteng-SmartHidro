package com.example.delotengsmarthidro.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import com.example.delotengsmarthidro.data.database.TutorDao
import com.example.delotengsmarthidro.data.database.TutorialEntity
import com.example.delotengsmarthidro.data.remote.ApiService
import com.example.delotengsmarthidro.data.response.WeatherResponse
import com.example.delotengsmarthidro.utils.await
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainRepository private constructor(
    private val apiService: ApiService,
    private val tutorDao: TutorDao,
    val context: Context
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
                appid = "a1d91f746849c525b26c14cf44cd7a6f",
            )
        } ?: throw Exception("Lokasi tidak ditemukan")
    }

    fun getAllTutor(): LiveData<List<TutorialEntity>> = tutorDao.getAllTutorial()

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(apiService: ApiService, context: Context, tutorDao: TutorDao) =
            instance ?: synchronized(this) {
                instance ?: MainRepository(apiService, tutorDao, context)
            }.also { instance = it }
    }
}