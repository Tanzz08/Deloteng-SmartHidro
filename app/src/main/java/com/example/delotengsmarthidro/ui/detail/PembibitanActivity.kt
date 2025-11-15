package com.example.delotengsmarthidro.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.delotengsmarthidro.R

class PembibitanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pembibitan)

        setupActionBar()

    }
    private fun setupActionBar() {
        (this as AppCompatActivity?)?.supportActionBar?.apply {
            hide()
        }
    }
}