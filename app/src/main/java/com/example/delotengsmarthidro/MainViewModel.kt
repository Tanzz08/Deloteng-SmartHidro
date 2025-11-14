package com.example.delotengsmarthidro

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delotengsmarthidro.data.database.TutorialEntity
import com.example.delotengsmarthidro.data.repository.MainRepository
import com.example.delotengsmarthidro.data.response.WeatherResponse
import com.example.delotengsmarthidro.utils.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(
    private val repository: MainRepository,
): ViewModel() {
    var croppedImageUri: Uri? = null
    var currentImageUri: Uri? = null

    private val _weatherResult = MutableLiveData<ResultState<WeatherResponse>>()
    val weatherResult: LiveData<ResultState<WeatherResponse>> = _weatherResult

    fun getWeatherData() {
        viewModelScope.launch {
            _weatherResult.value = ResultState.Loading
            try {
                val response = repository.getWeatherData()
                _weatherResult.value = ResultState.Success(response)
            } catch (e: HttpException) {
                _weatherResult.value = ResultState.Error(e.message())
            } catch (e: IOException) {
                _weatherResult.value = ResultState.Error("Tidak ada koneksi internet.")
            } catch (e: Exception) {
                _weatherResult.value = ResultState.Error(e.message ?: "Terjadi Kesalahan")
            }
        }
    }

    fun getAllTutor(): LiveData<List<TutorialEntity>> = repository.getAllTutor()
}