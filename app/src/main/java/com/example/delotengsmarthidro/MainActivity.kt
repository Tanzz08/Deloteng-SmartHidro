package com.example.delotengsmarthidro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.delotengsmarthidro.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_panduan, R.id.navigation_diagnose
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setupActionBar()

        // Cek apakah ini pertama kali aplikasi dibuka
        val preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val isFirstRun = preferences.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            // Jika pertama kali, tampilkan OnboardingActivity
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()  // Tutup LoginActivity
        }

        // Set flag supaya Onboarding tidak muncul lagi di masa depan
        val editor = preferences.edit()
        editor.putBoolean("isFirstRun", false)
        editor.apply()
    }

    private fun setupActionBar() {
        (this as AppCompatActivity?)?.supportActionBar?.apply {
            hide()
        }
    }

}