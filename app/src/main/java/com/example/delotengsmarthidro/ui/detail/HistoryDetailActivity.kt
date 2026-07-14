package com.example.delotengsmarthidro.ui.detail

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.viewmodel.ViewModelFactory
import com.example.delotengsmarthidro.MainViewModel
import com.example.delotengsmarthidro.data.database.HistoryEntity
import com.example.delotengsmarthidro.databinding.ActivityHistoryDetailBinding

class HistoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryDetailBinding
    private lateinit var viewModel: MainViewModel

    private var currentHistory: HistoryEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()

        val factory = ViewModelFactory.getInstance(this.application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        currentHistory = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_HISTORY, HistoryEntity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_HISTORY)
        }

        if (currentHistory != null) {
            currentHistory?.imageUri?.let {
                val imageUri = Uri.parse(it)
                binding.hero.setImageURI(imageUri)
            }

            val savedLabel = currentHistory?.label ?: ""
            val savedSeverity = currentHistory?.severity
            val savedSeverityExplanation = currentHistory?.severityExplanation
            val savedTimestamp = currentHistory?.timestamp

            if (savedTimestamp != null) {
                val dateFormat = java.text.SimpleDateFormat("dd MMMM yyyy, HH:mm", java.util.Locale("id", "ID"))
                val dateString = dateFormat.format(java.util.Date(savedTimestamp))
                binding.tvTimestamp.text = "Didiagnosa pada: $dateString"
                binding.tvTimestamp.visibility = View.VISIBLE

            }

            val savedScore = currentHistory?.confidenceScore

            if (savedScore != null && savedScore > 0f) {
                val percentage = (savedScore * 100).toInt()
                binding.tvConfidence.text = "Confidence Score: $percentage%"
                binding.tvConfidence.visibility = View.VISIBLE
            } else {
                binding.tvConfidence.visibility = View.GONE
            }

            binding.tvLabel.text = savedLabel

            // --- LOGIKA PEWARNAAN SEVERITY (Sama seperti ResultActivity) ---
            if (!savedSeverity.isNullOrEmpty()) {
                binding.tvSeverity.visibility = View.VISIBLE
                binding.tvSeverity.text = savedSeverity

                when {
                    savedSeverity.contains("🔴") -> {
                        binding.tvSeverity.setTextColor(android.graphics.Color.parseColor("#B71C1C"))
                        binding.tvSeverity.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#FFCDD2"))
                    }
                    savedSeverity.contains("🟠") -> {
                        binding.tvSeverity.setTextColor(android.graphics.Color.parseColor("#E65100"))
                        binding.tvSeverity.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#FFE0B2"))
                    }
                    savedSeverity.contains("🟡") -> {
                        binding.tvSeverity.setTextColor(android.graphics.Color.parseColor("#F57F17"))
                        binding.tvSeverity.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#FFF9C4"))
                    }
                    savedSeverity.contains("🟢") -> {
                        binding.tvSeverity.setTextColor(android.graphics.Color.parseColor("#1B5E20"))
                        binding.tvSeverity.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#C8E6C9"))
                    }
                    else -> {
                        binding.tvSeverity.setTextColor(android.graphics.Color.parseColor("#424242"))
                        binding.tvSeverity.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#EEEEEE"))
                    }
                }
            } else {
                binding.tvSeverity.visibility = View.GONE
            }

            // Tampilkan Penjelasan Severity
            if (!savedSeverityExplanation.isNullOrEmpty()) {
                binding.tvSeverityExplanation.visibility = View.VISIBLE
                binding.tvSeverityExplanation.text = savedSeverityExplanation
            } else {
                binding.tvSeverityExplanation.visibility = View.GONE
            }
            // --- AKHIR LOGIKA SEVERITY ---

            if (savedLabel == "Tanaman Sehat") {
                binding.apply {
                    cardPenyebab.visibility = View.GONE
                    cardCiri.visibility = View.VISIBLE // Tampilkan ciri untuk tanaman sehat
                    cardSolusi.visibility = View.VISIBLE

                    val solutionText = currentHistory?.treatment
                        ?.split("\n") // Ubah split("") menjadi split("\n") agar formatnya rapi
                        ?.joinToString(separator = "<br><br>") { "• $it" }
                        ?: "Solusi tidak tersedia"

                    binding.tvCaraMengatasi.text = "Cara Perawatan Lanjutan \u2705"
                    binding.tvSolution.text = Html.fromHtml(solutionText, Html.FROM_HTML_MODE_LEGACY)
                }
            } else if (savedLabel == "Bukan Daun Selada") {
                binding.apply {
                    cardPenyebab.visibility = View.GONE
                    cardCiri.visibility = View.GONE
                    tvSeverityExplanation.visibility = View.GONE // Sembunyikan penjelasan
                    cardSolusi.visibility = View.GONE
                }
            } else {
                binding.tvCaraMengatasi.text = "Cara Mengatasi \u2705"
            }

            binding.tvLabel.text = savedLabel

            val causesText = currentHistory?.causes
                ?.split("\n")
                ?.joinToString(separator = "<br><br>") { "• $it" }
                ?: "Data penyebab tidak tersedia"
            binding.tvCauses.text = Html.fromHtml(causesText, Html.FROM_HTML_MODE_LEGACY)

            // 2. Ambil Ciri-ciri dari Database
            val ciriText = currentHistory?.ciriCiri
                ?.split("\n")
                ?.joinToString(separator = "<br><br>") { "• $it" }
                ?: "Ciri-ciri tidak tersedia"
            binding.tvCiri.text = Html.fromHtml(ciriText, Html.FROM_HTML_MODE_LEGACY)

            // 3. Ambil Solusi dari Database
            val solutionText = currentHistory?.treatment
                ?.split("\n")
                ?.joinToString(separator = "<br><br>") { "• $it" }
                ?: "Solusi tidak tersedia"
            binding.tvSolution.text = Html.fromHtml(solutionText, Html.FROM_HTML_MODE_LEGACY)

            // Setup Interaktivitas Buka-Tutup Kartu
            setupExpandableCard(binding.cardPenyebab, binding.tvCauses)
            setupExpandableCard(binding.cardCiri, binding.tvCiri)
            setupExpandableCard(binding.cardSolusi, binding.tvSolution)

        } else {
            Log.e("HistoryDetailActivity", "HistoryEntity tidak diterima.")
            // Handle error UI
        }

        binding.btnHistoryOption.setOnClickListener { showHistoryMenu(it) }

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

    private fun showHistoryMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menu.add(0, 1, 0, "Hapus Riwayat")

        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                1 -> {
                    deleteHistoryData()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun deleteHistoryData() {
        if (currentHistory == null) {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
            return
        }

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Hapus Riwayat")
            .setMessage("Apakah Anda yakin ingin menghapus riwayat diagnosa ini?")
            .setPositiveButton("Hapus") { _, _ ->

                currentHistory?.let { historyToDelete ->
                    viewModel.delete(historyToDelete)
                }

                Toast.makeText(this, "Riwayat berhasil dihapus", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun setupActionBar() {
        supportActionBar?.hide()
    }

    companion object {
        const val EXTRA_HISTORY = "extra_history"
    }
}