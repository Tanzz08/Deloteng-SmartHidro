package com.dicoding.asclepius.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.delotengsmarthidro.MainViewModel
import com.example.delotengsmarthidro.data.repository.MainRepository

class ViewModelFactory private constructor(private val repository: MainRepository): ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(repository: MainRepository): ViewModelFactory{
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(repository)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNREACHABLE_CODE", "UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}