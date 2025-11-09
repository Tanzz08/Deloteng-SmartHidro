package com.example.delotengsmarthidro.ui.diagnose

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.media.Image
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
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.viewmodel.ViewModelFactory
import com.example.delotengsmarthidro.MainViewModel
import com.example.delotengsmarthidro.ResultActivity
import com.example.delotengsmarthidro.databinding.FragmentDiagnoseBinding
import com.example.delotengsmarthidro.helper.ImageClassifierHelper
import com.yalantis.ucrop.UCrop
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.io.File
import java.text.NumberFormat

class DiagnoseFragment : Fragment() {

    private var _binding: FragmentDiagnoseBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageClassifierHelper: ImageClassifierHelper
    private lateinit var viewModel: MainViewModel
//    private var currentImageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        _binding = FragmentDiagnoseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showImage()

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
                            progressIndicator.visibility = View.GONE
                            showToast(error)
                        }
                    }

                    override fun onResults(results: List<Classifications>?) {
                        binding.progressIndicator.visibility = View.GONE
                        results?.let { it ->
                            if (it.isNotEmpty() && it[0].categories.isNotEmpty()) {
                                Log.d("Classification Result", it.toString())

                                val label = it[0].categories[0].label

                                moveToResult(label)
                            } else {
                                showToast("Tidak dapat menemukan hasil")
                            }
                        }
                    }

                }
            )
        }
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

        // Pastikan launcherIntentCamera sudah didefinisikan
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

    // BENAR: Menganalisis gambar yang sudah di-crop
    private fun analyzeImage() {
        binding.progressIndicator.visibility = View.VISIBLE

        // Gunakan URI yang sudah di-crop
        viewModel.croppedImageUri?.let { uri ->
            imageClassifierHelper.classifyStaticImage(uri)
        } ?: showToast("Silakan masukkan berkas gambar terlebih dahulu.")
    }

    private fun moveToResult(result: String) {
        val intent = Intent(requireActivity(), ResultActivity::class.java)
        intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, viewModel.croppedImageUri.toString())
        intent.putExtra(ResultActivity.EXTRA_RESULT, result)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 1. Ini adalah solusi untuk permintaan Anda:
        //    Membersihkan URI di ViewModel saat view dihancurkan.
        viewModel.croppedImageUri = null
        viewModel.currentImageUri = null // Bersihkan juga URI aslinya jika perlu

        // 2. Ini adalah perbaikan penting untuk kode Anda:
        //    Selalu atur _binding = null di onDestroyView()
        //    untuk menghindari kebocoran memori (memory leaks).
        _binding = null
    }
}