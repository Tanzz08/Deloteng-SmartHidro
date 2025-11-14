package com.example.delotengsmarthidro.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
// import com.bumptech.glide.Glide // Kita tidak butuh Glide untuk UI utama
import com.dicoding.asclepius.viewmodel.ViewModelFactory
import com.example.delotengsmarthidro.MainViewModel
import com.example.delotengsmarthidro.ResultActivity
import com.example.delotengsmarthidro.adapter.WeatherAdapter
import com.example.delotengsmarthidro.data.di.Injection
import com.example.delotengsmarthidro.data.response.WeatherResponse
import com.example.delotengsmarthidro.databinding.FragmentHomeBinding
import com.example.delotengsmarthidro.utils.ResultState
import com.example.delotengsmarthidro.utils.formDate // Pastikan util ini ada
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // (Launcher izin tetap sama)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)) {
            viewModel.getWeatherData()
        } else {
            Toast.makeText(requireActivity(), "Akses lokasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val repository = Injection.provideMainRepository(requireContext())
        val factory = ViewModelFactory.getInstance(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(MainViewModel::class.java)

        setupRecyclerView()
        observerWeather()
        requestWeatherByLocation()
    } 

    // (Fungsi requestWeatherByLocation dan isLocationPermissionGranted tetap sama)
    private fun requestWeatherByLocation() {
        if (isLocationPermissionGranted()) {
            viewModel.getWeatherData()
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setupRecyclerView() {
        weatherAdapter = WeatherAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = weatherAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observerWeather() {
        viewModel.weatherResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    binding.progressBar.visibility = View.GONE

                    val weatherResponse = result.data

                    // Panggil fungsi untuk update UI cuaca saat ini
                    updateCurrentWeatherUI(weatherResponse) // <-- PERUBAHAN DI SINI

                    // Filter 5 hari untuk RecyclerView
                    val dailyForecastIndices = listOf(0, 8, 16, 24, 32)
                    val dailyList = weatherResponse.list.filterIndexed { index, _ ->
                        index in dailyForecastIndices
                    }
                    weatherAdapter.submitList(dailyList)
                }
                is ResultState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // --- FUNGSI INI SUDAH DISESUAIKAN DENGAN XML ANDA ---
    @SuppressLint("SetTextI18n", "DefaultLocale")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateCurrentWeatherUI(weather: WeatherResponse) {
        val currentDateTime = ZonedDateTime.now(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        val matchWeather = weather.list.firstOrNull { forecast ->
            val forecastDateTime = LocalDateTime.parse(forecast.dtTxt, formatter)
                .atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.systemDefault())
            forecastDateTime.isAfter(currentDateTime) || forecastDateTime.isEqual(currentDateTime)
        }

        matchWeather?.let { forecast ->
            // Konversi Kelvin ke Celsius (WAJIB, karena API tidak 'metric')
            val tempInCelcius = forecast.main.temp - 273.15

            // 1. Set Suhu (ID: tvTemp)
            binding.tvTemp.text = String.format("%.0f°C", tempInCelcius)

            // 2. Set Tanggal (ID: tvDate)
            binding.tvDate.text = formDate(forecast.dtTxt)

            // 3. Set Deskripsi Cuaca (ID: tvCuaca)
            if (forecast.weather.isNotEmpty()) {
                val description = forecast.weather[0].description
                // Mengubah "light rain" -> "Light Rain"
                binding.tvCuaca.text = description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}