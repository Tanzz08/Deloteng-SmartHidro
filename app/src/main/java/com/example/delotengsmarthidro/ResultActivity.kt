package com.example.delotengsmarthidro

import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.delotengsmarthidro.data.list.disease.DiseaseData
import com.example.delotengsmarthidro.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()

        val label = intent.getStringExtra(EXTRA_RESULT)
        val imageUriString = intent.getStringExtra(EXTRA_IMAGE_URI)
        Log.d("ResultActivity_Debug", "Label yang diterima: '$label'")
        imageUriString?.let {
            val imageUri = Uri.parse(it)
            binding.hero.setImageURI(imageUri)
        }

        if (label != null) {
            val cleanLabel = label.trim()
            val disease = DiseaseData.findByLabel(cleanLabel)
            if (disease != null) {
                binding.apply {
                    tvLabel.text = disease.displayName

                    val ciriText = disease.characteristics.joinToString(separator = "<br><br>") { item ->
                        "⚠️ $item"
                    }
                    val solutionText = disease.solution.joinToString(separator = "<br><br>") { item ->
                        "✅ $item"
                    }
                    val formattedCiri = Html.fromHtml(ciriText, Html.FROM_HTML_MODE_LEGACY)
                    val formattedSolution = Html.fromHtml(solutionText, Html.FROM_HTML_MODE_LEGACY)

                    tvCiri.text = formattedCiri
                    tvSolution.text = formattedSolution
                }
            } else {
                binding.tvLabel.text = label
                binding.tvCiri.text = "Data penyakit tidak ditemukan"
                binding.tvSolution.text = "Silakan periksa kembali gambar Anda"
            }
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
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
    }
}