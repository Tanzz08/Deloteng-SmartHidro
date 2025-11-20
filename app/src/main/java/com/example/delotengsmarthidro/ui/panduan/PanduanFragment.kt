package com.example.delotengsmarthidro.ui.panduan

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.viewmodel.ViewModelFactory
import com.example.delotengsmarthidro.MainViewModel
import com.example.delotengsmarthidro.MyApplication
import com.example.delotengsmarthidro.R
import com.example.delotengsmarthidro.adapter.TipsListAdapter
import com.example.delotengsmarthidro.adapter.TutorListAdapter
import com.example.delotengsmarthidro.data.database.TutorialEntity
import com.example.delotengsmarthidro.data.list.tips.DummyTipsData
import com.example.delotengsmarthidro.data.list.tutorial.DummyTutorialData
import com.example.delotengsmarthidro.databinding.FragmentPanduanBinding
import com.example.delotengsmarthidro.ui.detail.DetailActivity
import com.example.delotengsmarthidro.ui.detail.NutrisiActivity
import com.example.delotengsmarthidro.ui.detail.PembibitanActivity
import com.example.delotengsmarthidro.ui.detail.PerawatanActivity
import com.example.delotengsmarthidro.ui.detail.TransplantasiActivity

class PanduanFragment : Fragment() {

    private var _binding: FragmentPanduanBinding? = null
    private val binding get() = _binding!!
    private var player:ExoPlayer? = null

    private val mainViewModel: MainViewModel by viewModels {
        val repository = (requireActivity().application as MyApplication).repository
        ViewModelFactory.getInstance(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPanduanBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            contentContainer.setOnClickListener {
                navigateToDetail(DetailActivity::class.java)
            }
            tutor2.setOnClickListener {
                navigateToDetail(PembibitanActivity::class.java)
            }
            tutor3.setOnClickListener {
                navigateToDetail(NutrisiActivity::class.java)
            }
            tutor4.setOnClickListener {
                navigateToDetail(TransplantasiActivity::class.java)
            }
            tutor5.setOnClickListener {
                navigateToDetail(PerawatanActivity::class.java)
            }
        }

        val tipsData = DummyTipsData.getTipsSolution()
        val tipsAdapter = TipsListAdapter(tipsData)
        setupRecyclerView(binding.rvTips, tipsAdapter)

        initializePlayer()
    }

    private fun navigateToDetail(destinationActivity: Class<*>) {
        val intent = Intent(requireContext(), destinationActivity)
        startActivity(intent)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
    }

    private fun initializePlayer() {
        val uri = Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.vid_tutor}")
        player = ExoPlayer.Builder(requireContext()).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
        }
        binding.apply {
            frameVid.player = player
        }
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        player?.release()
        player = null
        _binding = null
    }
}
