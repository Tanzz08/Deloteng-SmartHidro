package com.example.delotengsmarthidro.ui.detail

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.delotengsmarthidro.data.database.HistoryEntity
import com.example.delotengsmarthidro.databinding.ActivityResultBinding

class HistoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()

        val history = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_HISTORY, HistoryEntity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_HISTORY)
        }

        if (history != null) {
            history.imageUri?.let {
                val imageUri = Uri.parse(it)
                binding.hero.setImageURI(imageUri)
            }
            binding.tvLabel.text = history.label

            val ciriText = history.ciriCiri
                ?.split("\n")
                ?.joinToString(separator = "<br><br>") { item -> "⚠️ $item" }
                ?: "Ciri-ciri tidak tersedia"

            binding.tvCiri.text = Html.fromHtml(ciriText, Html.FROM_HTML_MODE_LEGACY)


            val solutionText = history.treatment
                ?.split("\n")
                ?.joinToString(separator = "<br><br>") { item -> "✅ $item" }
                ?: "Solusi tidak tersedia"

            binding.tvSolution.text = Html.fromHtml(solutionText, Html.FROM_HTML_MODE_LEGACY)

        } else {
            Log.e("HistoryDetailActivity", "HistoryEntity tidak diterima.")
            binding.tvLabel.text = "Error"
            binding.tvCiri.text = "Data riwayat tidak ditemukan."
            binding.tvSolution.text = ""
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupActionBar() {
        (this as AppCompatActivity?)?.supportActionBar?.apply {
            hide()
        }
    }

    companion object {
        const val EXTRA_HISTORY = "extra_history"
    }
}