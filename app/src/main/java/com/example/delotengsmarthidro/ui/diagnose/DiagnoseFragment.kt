package com.example.delotengsmarthidro.ui.diagnose

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.delotengsmarthidro.MainViewModel
import com.example.delotengsmarthidro.ViewModelFactory
import com.example.delotengsmarthidro.databinding.FragmentDiagnoseBinding
import com.example.delotengsmarthidro.helper.ImageClassifierHelper
import com.yalantis.ucrop.UCrop

class DiagnoseFragment : Fragment() {

    private var _binding: FragmentDiagnoseBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageClassifierHelper: ImageClassifierHelper
    private lateinit var viewModel: MainViewModel

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

    private fun startCrop(uri: Uri) {
        val destinationUri = Uri.fromFile(requireContext().cacheDir, "cropped_image.jpg")
    }

    private fun startGallery() {

    }

    private fun showImage() {
        viewModel.croppedImageUri?.let {
            binding.apply {
                ivPicture.setImageURI(null)
                ivPicture.setImageURI(it)
            }
        }
    }


}