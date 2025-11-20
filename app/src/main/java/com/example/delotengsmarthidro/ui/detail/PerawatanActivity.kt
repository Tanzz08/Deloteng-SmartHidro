package com.example.delotengsmarthidro.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.delotengsmarthidro.R
import com.example.delotengsmarthidro.databinding.ActivityPerawatanBinding

class PerawatanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerawatanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPerawatanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupActionBar() {
        (this as AppCompatActivity?)?.supportActionBar?.apply {
            hide()
        }
    }
}