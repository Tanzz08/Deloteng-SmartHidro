package com.example.delotengsmarthidro

import android.app.Application
import android.net.Uri
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application): ViewModel() {
    var croppedImageUri: Uri? = null
    var currentImageUri: Uri? = null


}