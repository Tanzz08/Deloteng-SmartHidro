package com.example.delotengsmarthidro

import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.delotengsmarthidro.data.list.disease.DiseaseData
import com.example.delotengsmarthidro.databinding.ActivityResultBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

        val timestamp = intent.getLongExtra(EXTRA_TIMESTAMP, 0L)
        if (timestamp != 0L) {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale("id", "ID"))
            val dateString = dateFormat.format(Date(timestamp))
            binding.tvTimestamp.text = "Didiagnosa pada: $dateString"
            binding.tvTimestamp.visibility = View.VISIBLE
        }

        val score = intent.getFloatExtra(EXTRA_SCORE, 0f)
        if(score > 0f) {
            val percentage = (score * 100).toInt()
            binding.tvConfidence.text = "Confidence Score: $percentage%"
            binding.tvConfidence.visibility = View.VISIBLE
        } else {
            binding.tvConfidence.visibility = View.GONE
        }

        imageUriString?.let {
            val imageUri = Uri.parse(it)
            binding.hero.setImageURI(imageUri)
        }

        if (label != null) {
            val cleanLabel = label.trim()
            val disease = DiseaseData.findByLabel(cleanLabel)

            if (disease != null) {
                binding.apply {
                    // 1. Set Label dan Tingkat Bahaya
                    tvLabel.text = disease.displayName
                    tvSeverity.text = disease.severity // Memasukkan data severity
                    tvSeverityExplanation.text = disease.severityExplanation // TAMBAHKAN BARIS INI

                    // 2. Logika Pewarnaan Dinamis untuk Tingkat Bahaya (tvSeverity)
                    when {
                        disease.severity.contains("🔴") -> {
                            tvSeverity.setTextColor(Color.parseColor("#B71C1C")) // Merah gelap
                            tvSeverity.backgroundTintList =
                                ColorStateList.valueOf(Color.parseColor("#FFCDD2")) // Background merah muda
                        }

                        disease.severity.contains("🟠") -> {
                            tvSeverity.setTextColor(Color.parseColor("#E65100")) // Orange gelap
                            tvSeverity.backgroundTintList =
                                ColorStateList.valueOf(Color.parseColor("#FFE0B2"))
                        }

                        disease.severity.contains("🟡") -> {
                            tvSeverity.setTextColor(Color.parseColor("#F57F17")) // Kuning gelap
                            tvSeverity.backgroundTintList =
                                ColorStateList.valueOf(Color.parseColor("#FFF9C4"))
                        }

                        disease.severity.contains("🟢") -> {
                            tvSeverity.setTextColor(Color.parseColor("#1B5E20")) // Hijau gelap
                            tvSeverity.backgroundTintList =
                                ColorStateList.valueOf(Color.parseColor("#C8E6C9"))
                        }

                        else -> {
                            tvSeverity.setTextColor(Color.parseColor("#424242")) // Abu-abu gelap
                            tvSeverity.backgroundTintList =
                                ColorStateList.valueOf(Color.parseColor("#EEEEEE"))
                        }
                    }

                    // 3. Konfigurasi Tampilan Berdasarkan Tipe Penyakit
                    when (disease.modelKey) {
                        "healthy" -> {
                            cardPenyebab.visibility = View.GONE
                            cardCiri.visibility = View.VISIBLE
                            tvSeverityExplanation.visibility = View.VISIBLE // Pastikan terlihat
                            tvCaraMengatasi.text = "Cara Perawatan Lanjutan \u2705"
                        }

                        "bukan_daun_selada" -> {
                            cardPenyebab.visibility = View.GONE
                            cardCiri.visibility = View.GONE
                            cardSolusi.visibility = View.GONE
                            tvSeverityExplanation.visibility = View.GONE // Sembunyikan penjelasan

                        }

                        else -> {
                            // Tampilkan semua untuk penyakit
                            cardPenyebab.visibility = View.VISIBLE
                            cardCiri.visibility = View.VISIBLE
                            tvSeverityExplanation.visibility = View.VISIBLE // Pastikan terlihat
                            tvCaraMengatasi.text = "Cara Mengatasi \u2705"
                        }
                    }

                    // 4. Format String dengan spasi antar poin
                    val causesText = disease.causes.joinToString(separator = "<br><br>") { "• $it" }
                    val ciriText =
                        disease.characteristics.joinToString(separator = "<br><br>") { "• $it" }
                    val solutionText =
                        disease.solution.joinToString(separator = "<br><br>") { "• $it" }

                    // 5. Terapkan Html formatting
                    tvCauses.text = Html.fromHtml(causesText, Html.FROM_HTML_MODE_LEGACY)
                    tvCiri.text = Html.fromHtml(ciriText, Html.FROM_HTML_MODE_LEGACY)
                    tvSolution.text = Html.fromHtml(solutionText, Html.FROM_HTML_MODE_LEGACY)

                    // 6. Setup Expand/Collapse Interactivity
                    setupExpandableCard(cardPenyebab, tvCauses)
                    setupExpandableCard(cardCiri, tvCiri)
                    setupExpandableCard(cardSolusi, tvSolution)
                }
            } else {
                binding.tvLabel.text = label
                binding.tvSeverity.visibility = View.GONE // Sembunyikan jika tidak ada data
                binding.tvCiri.text = "Data penyakit tidak ditemukan"
                binding.tvSolution.text = "Silakan periksa kembali gambar Anda"
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    // Fungsi untuk membuat animasi buka tutup teks
    private fun setupExpandableCard(cardView: View, textView: View) {
        cardView.setOnClickListener {
            if (textView.visibility == View.VISIBLE) {
                textView.visibility = View.GONE
            } else {
                textView.visibility = View.VISIBLE
            }
        }
    }

    private fun setupActionBar() {
        supportActionBar?.hide()
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_TIMESTAMP = "extra_timestamp"
        const val EXTRA_SCORE = "extra_score"
    }
}