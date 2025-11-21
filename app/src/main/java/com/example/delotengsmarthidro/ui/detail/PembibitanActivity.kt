package com.example.delotengsmarthidro.ui.detail

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.delotengsmarthidro.R
import com.example.delotengsmarthidro.databinding.ActivityPembibitanBinding

class PembibitanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembibitanBinding
    private var player:ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPembibitanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        initializePlayer()

    }
    private fun setupActionBar() {
        (this as AppCompatActivity?)?.supportActionBar?.apply {
            hide()
        }
    }

    private fun initializePlayer() {
        val uri = Uri.parse("android.resource://${this.packageName}/${R.raw.tutor_semai}")
        player = ExoPlayer.Builder(this).build().apply {
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


    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}