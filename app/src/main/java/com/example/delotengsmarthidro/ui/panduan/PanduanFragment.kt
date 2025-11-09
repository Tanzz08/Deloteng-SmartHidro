package com.example.delotengsmarthidro.ui.panduan

import android.annotation.SuppressLint
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
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delotengsmarthidro.R
import com.example.delotengsmarthidro.adapter.TipsListAdapter
import com.example.delotengsmarthidro.adapter.TutorListAdapter
import com.example.delotengsmarthidro.data.list.tips.DummyTipsData
import com.example.delotengsmarthidro.data.list.tutorial.DummyTutorialData
import com.example.delotengsmarthidro.databinding.FragmentPanduanBinding

class PanduanFragment : Fragment() {

    private var _binding: FragmentPanduanBinding? = null
    private val binding get() = _binding!!
    private var player:ExoPlayer? = null

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

        val tutorialData = DummyTutorialData.getTutorialSteps()
        val tutorialAdapter = TutorListAdapter(tutorialData)
        setupRecyclerView(binding.rvTuorial, tutorialAdapter)

        val tipsData = DummyTipsData.getTipsSolution()
        val tipsAdapter = TipsListAdapter(tipsData)

        setupRecyclerView(binding.rvTips, tipsAdapter)

        initializePlayer()
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
