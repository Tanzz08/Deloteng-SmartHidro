package com.example.delotengsmarthidro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.delotengsmarthidro.R
import com.example.delotengsmarthidro.data.response.ListItem
import com.example.delotengsmarthidro.databinding.ListCuacaBinding
import com.example.delotengsmarthidro.utils.formatDateInDay

class WeatherAdapter : ListAdapter<ListItem, WeatherAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ListCuacaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: ListItem) {
            binding.apply {
                tvDay.text = formatDateInDay(weather.dtTxt)

                // --- PENTING: LAKUKAN KONVERSI KELVIN KE CELSIUS ---
                val tempInKelvin = weather.main.temp
                val tempInCelsius = tempInKelvin - 273.15 // (atau 273)
                // ---------------------------------------------------

                // Tampilkan suhu yang sudah dikonversi
                tvTemp.text = String.format("%.0f°C", tempInCelsius)

                val iconCode = weather.weather[0].icon
                val iconUrl = "https://openweathermap.org/img/wn/$iconCode@2x.png"

                Glide.with(itemView.context)
                    .load(iconUrl)
                    .placeholder(R.drawable.ic_launcher_background) // Ganti placeholder
                    .error(R.drawable.ic_launcher_background) // Ganti error image
                    .into(imgWeather)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListCuacaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem.dt == newItem.dt
            }
            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}