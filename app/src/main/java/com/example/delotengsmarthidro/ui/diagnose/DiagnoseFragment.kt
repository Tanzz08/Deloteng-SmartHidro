package com.example.delotengsmarthidro.ui.diagnose

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.viewmodel.ViewModelFactory
import com.example.delotengsmarthidro.MainViewModel
import com.example.delotengsmarthidro.ResultActivity
import com.example.delotengsmarthidro.adapter.HistoryAdapter
import com.example.delotengsmarthidro.data.list.disease.DiseaseData
import com.example.delotengsmarthidro.data.database.HistoryEntity
import com.example.delotengsmarthidro.data.di.Injection
import com.example.delotengsmarthidro.databinding.FragmentDiagnoseBinding
import com.example.delotengsmarthidro.helper.ImageClassifierHelper
import com.example.delotengsmarthidro.ui.detail.HistoryDetailActivity
import com.yalantis.ucrop.UCrop
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.io.File
import java.io.FileOutputStream

class DiagnoseFragment : Fragment() {

    private var _binding: FragmentDiagnoseBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageClassifierHelper: ImageClassifierHelper
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: HistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        _binding = FragmentDiagnoseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showImage()

        adapter = HistoryAdapter { history ->
            val intent = Intent(requireActivity(), HistoryDetailActivity::class.java)
            intent.putExtra(HistoryDetailActivity.EXTRA_HISTORY, history)
            startActivity(intent)
        }

        viewModel.allHistory.observe(viewLifecycleOwner) { historyList ->
            adapter.submitList(historyList)
        }

        binding.apply {
            btnUploadImage.setOnClickListener{
                startGallery()
            }
            btnAmbilGambar.setOnClickListener{
                startCamera()
            }
            binding.btnDiagnose.setOnClickListener {
                analyzeImage()
            }

            imageClassifierHelper = ImageClassifierHelper(
                context = requireActivity(),
                classifierListener = object : ImageClassifierHelper.ClassifierListener {
                    override fun onError(error: String) {
                        activity?.runOnUiThread {
                            binding.progressIndicator.visibility = View.GONE
                            showToast(error)
                            setUiInteraction(true)
                        }
                    }

                    override fun onResults(results: List<Classifications>?) {
                        binding.progressIndicator.visibility = View.GONE
                        setUiInteraction(true)

                        val currentImageUri = viewModel.croppedImageUri // Ini URI cache sementara

                        if (currentImageUri == null) {
                            Log.e("DiagnoseFragment", "Image URI null when onResults returned.")
                            showToast("Gagal menyimpan riwayat, URI tidak ditemukan.")
                            return
                        }

                        // **PERUBAHAN KUNCI 1: SALIN GAMBAR KE FILE PERMANEN**
                        val permanentImageUri = saveImageToInternalStorage(requireContext(), currentImageUri)
                        if (permanentImageUri == null) {
                            Log.e("DiagnoseFragment", "Failed to save image to internal storage.")
                            showToast("Gagal menyimpan gambar riwayat.")
                            return
                        }
                        // **SELESAI**

                        results?.let {
                            if (it.isNotEmpty() && it[0].categories.isNotEmpty()) {
                                Log.d("Classification Result", it.toString())

                                val label = it[0].categories[0].label
                                val disease = DiseaseData.findByLabel(label)

                                // **PERUBAHAN KUNCI 2: GUNAKAN URI PERMANEN**
                                val imageUriString = permanentImageUri.toString()
                                val timestamp = System.currentTimeMillis()
                                var treatmentString: String? = null
                                var ciriCiriString: String? = null

                                disease?.let { d ->
                                    ciriCiriString = d.characteristics.joinToString("\n")
                                    treatmentString = d.solution.joinToString("\n")
                                        .replace("<b>", "")
                                        .replace("</b>", "")
                                }

                                val history = HistoryEntity(
                                    imageUri = imageUriString, // <-- Simpan URI permanen
                                    label = disease?.displayName ?: label,
                                    timestamp = timestamp,
                                    treatment = treatmentString,
                                    ciriCiri = ciriCiriString
                                )

                                viewModel.insertHistory(history)

                                // Kirim URI permanen ke ResultActivity
                                moveToResult(label, imageUriString)
                            } else {
                                showToast("Pastikan Foto Daun Terlihat Jelas")
                            }
                        }
                    }

                }
            )

            rvHistory.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            rvHistory.adapter = adapter

        }
    }

    // **FUNGSI BARU UNTUK MENYIMPAN GAMBAR**
    private fun saveImageToInternalStorage(context: Context, uri: Uri): Uri? {
        // Gunakan 'safe' context
        val appContext = context.applicationContext ?: return null
        val contentResolver = appContext.contentResolver
        val inputStream = contentResolver.openInputStream(uri) ?: return null

        // Buat direktori jika belum ada
        val directory = File(appContext.filesDir, "history_images")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        // Buat file tujuan yang unik
        val file = File(directory, "IMG_${System.currentTimeMillis()}.jpg")

        // Salin file
        try {
            val outputStream = FileOutputStream(file)
            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: Exception) {
            Log.e("SaveImage", "Error copying file", e)
            return null
        }

        // Kembalikan URI dari file yang BARU
        return file.toUri()
    }
    // **AKHIR FUNGSI BARU**

    private fun setUiInteraction(isEnabled: Boolean) {
        binding.btnUploadImage.isEnabled = isEnabled
        binding.btnAmbilGambar.isEnabled = isEnabled
        binding.btnDiagnose.isEnabled = isEnabled
    }

    // mendapatkan hasil uCrop
    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onActivityResult(requestCode, resultCode, data)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            // Ini tetap URI cache sementara, tidak apa-apa
            viewModel.croppedImageUri = UCrop.getOutput(data!!)
            showImage()
            binding.btnDiagnose.isEnabled = true
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            cropError?.let { Log.e("UCrop Error", it.message.toString()) }
        } else if (resultCode == RESULT_CANCELED && requestCode == UCrop.REQUEST_CROP) {
            viewModel.croppedImageUri = null
            binding.btnDiagnose.isEnabled = false
        }
    }

    private fun startCrop(uri: Uri) {
        // Ini adalah file cache sementara, ini sudah benar
        val destinationUri = Uri.fromFile(File(requireContext().cacheDir, "cropped_image.jpg"))
        UCrop.of(uri, destinationUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1080, 1080)
            .start(requireContext(), this)
    }

    private val launchGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.currentImageUri = uri
            startCrop(uri)
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startGallery() {
        launchGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            viewModel.currentImageUri?.let { uri -> startCrop(uri) }
        } else {
            viewModel.currentImageUri = null
        }
    }
    private fun startCamera() {
        val context = requireContext() // Ambil context di sini
        val file = File(context.cacheDir, "IMG_${System.currentTimeMillis()}.jpg")

        // Gunakan 'context' untuk mendapatkan packageName
        viewModel.currentImageUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        // Pastikan launcherIntentCamera sudah didefinikan
        launcherIntentCamera.launch(viewModel.currentImageUri!!)
    }

    private fun showImage() {
        viewModel.croppedImageUri?.let {
            binding.apply {
                ivPicture.visibility = View.GONE
                tv2.visibility = View.GONE
                textView.visibility = View.GONE
                ivPreview.setImageURI(null)
                ivPreview.setImageURI(it)
            }
        }
    }

    // Mengunci UI saat diagnosis dimulai
    private fun analyzeImage() {
        binding.progressIndicator.visibility = View.VISIBLE
        setUiInteraction(false) // KUNCI UI DI SINI

        viewModel.croppedImageUri?.let { uri ->
            imageClassifierHelper.classifyStaticImage(uri)
        } ?: run {
            showToast("Silakan masukkan berkas gambar terlebih dahulu.")
            binding.progressIndicator.visibility = View.GONE
            setUiInteraction(true) // BUKA KUNCI UI JIKA GAGAL
        }
    }

    // Ubah parameter fungsi ini
    private fun moveToResult(result: String, imageUriString: String) {
        val intent = Intent(requireActivity(), ResultActivity::class.java)
        intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, imageUriString) // Gunakan parameter
        intent.putExtra(ResultActivity.EXTRA_RESULT, result)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}