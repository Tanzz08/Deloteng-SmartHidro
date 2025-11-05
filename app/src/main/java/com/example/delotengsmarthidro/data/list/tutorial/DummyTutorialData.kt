package com.example.delotengsmarthidro.data.list.tutorial

import com.example.delotengsmarthidro.R

object DummyTutorialData {
    fun getTutorialSteps(): List<TutorList> {
        return listOf(
            TutorList(
                title = "1. Persiapan Alat dan Bahan",
                desc = "Siapkan benih, rockwool atau media semai, netpot, wadah nutrisi, dan nutrisi hidroponik. Gunakan barang bekas seperti botol atau ember jika perlu.",
                icon = R.drawable.ic_truc
            ),
            TutorList(
                title = "2. Penyemaian",
                desc = "Basahi rockwool, masukkan 1-2 benih per lubang, tutup tipis. Simpan di tempat teduh, semprot pagi & sore. Bibit muncul 1-7 hari.",
                icon = R.drawable.ic_tabler_seeding
            ),
            TutorList(
                title = "3. Transplant & Setup",
                desc = "Pindahkan bibit saat 2-4 daun sejati. Pasang netpot di rakit atau sistem wick sehingga akar menyentuh larutan nutrisi.",
                icon = R.drawable.ic_transform
            ),
            TutorList(
                title = "4. Perawatan Rutin",
                desc = "Cek larutan nutrisi setiap 2-3 hari, jaga ketinggian air. Beri sinar 4-6 jam/hari. Ganti larutan penuh setiap 7-10 hari bila perlu.",
                icon = R.drawable.ic_weather
            ),
        )
    }
}