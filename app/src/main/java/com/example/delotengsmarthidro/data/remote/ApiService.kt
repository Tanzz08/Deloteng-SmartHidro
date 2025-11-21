package com.example.delotengsmarthidro.data.remote

import com.example.delotengsmarthidro.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("lang") lang: String = "id"
    ): WeatherResponse
}