package com.example.delotengsmarthidro.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import com.example.delotengsmarthidro.data.database.HistoryDao
import com.example.delotengsmarthidro.data.database.HistoryEntity
// HAPUS import TutorDao dan TutorialEntity
// import com.example.delotengsmarthidro.data.database.TutorDao
// import com.example.delotengsmarthidro.data.database.TutorialEntity
import com.example.delotengsmarthidro.data.remote.ApiService
import com.example.delotengsmarthidro.data.response.WeatherResponse
import com.example.delotengsmarthidro.utils.await
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainRepository private constructor(
    private val apiService: ApiService,
    // HAPUS tutorDao
    // private val tutorDao: TutorDao,
    private val historyDao: HistoryDao,
    val context: Context
){
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }
    private val executorsService: ExecutorService = Executors.newSingleThreadExecutor()

    // Fungsi insert dan delete untuk history sudah benar
    suspend fun insert(history: HistoryEntity){
        executorsService.execute { historyDao.insert(history) }
    }

    suspend fun delete(history: HistoryEntity){
        executorsService.execute { historyDao.delete(history)}
    }

    fun getAllHistory(): LiveData<List<HistoryEntity>> = historyDao.getAllHistory()

    // Fungsi getWeatherData sudah benar
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

    // HAPUS fungsi yang menggunakan tutorDao
    // fun getAllTutor(): LiveData<List<TutorialEntity>> = tutorDao.getAllTutorial()

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: MainRepository? = null
        // HAPUS tutorDao dari parameter
        fun getInstance(apiService: ApiService, context: Context, historyDao: HistoryDao) =
            instance ?: synchronized(this) {
                // HAPUS tutorDao dari panggilan constructor
                instance ?: MainRepository(apiService, historyDao, context)
            }.also { instance = it }
    }
}