package com.example.delotengsmarthidro.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.delotengsmarthidro.R
import com.example.delotengsmarthidro.data.database.TutorialEntity
import com.example.delotengsmarthidro.databinding.ActivityDetailBinding
import android.graphics.drawable.Drawable // <-- Tambahkan ini
import android.text.Html // <-- Tambahkan ini
import java.io.IOException // <-- Tambahkan ini

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()



    }

    private fun setupActionBar() {
        (this as AppCompatActivity?)?.supportActionBar?.apply {
            hide()
        }
    }

    companion object {
        const val EXTRA_TUTORIAL = "extra_tutorial"
    }
}