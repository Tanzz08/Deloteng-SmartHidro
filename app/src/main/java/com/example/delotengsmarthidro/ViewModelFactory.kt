package com.dicoding.asclepius.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.delotengsmarthidro.MainViewModel
import com.example.delotengsmarthidro.data.di.Injection
import com.example.delotengsmarthidro.data.repository.MainRepository
import com.example.delotengsmarthidro.preference.Preference

class ViewModelFactory private constructor(private val repository: MainRepository, private val mPreference: Preference): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNREACHABLE_CODE", "UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository, mPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(
                    Injection.provideMainRepository(context),
                    Injection.userPreference(context)
                )
            }.also { instance = it }
    }
}