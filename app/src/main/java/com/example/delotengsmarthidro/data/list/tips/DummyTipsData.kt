package com.example.delotengsmarthidro.data.list.tips

import com.example.delotengsmarthidro.R

object DummyTipsData {
    fun getTipsSolution(): List<TipsList> {
        return listOf(
            TipsList(
                title = "Gunakan Benih Berkualitas dan Steril",
                desc = "Pastikan benih berasal dari sumber terpercaya dan bebas patogen untuk mengurangi risiko penyakit sejak awal pertumbuhan.",
                icon = R.drawable.ic_truc
            ),
            TipsList(
                title = "Sterilisasi Media Tanam",
                desc = "Gunakan media bersih seperti rockwool atau cocopeat–perlite dan pastikan sudah disterilisasi untuk mencegah kontaminasi jamur dan bakteri.",
                icon = R.drawable.ic_tabler_seeding
            ),
            TipsList(
                title = "Kebersihan Alat dan Wadah",
                desc = "Bersihkan tray, pot net, dan wadah nutrisi secara rutin menggunakan larutan desinfektan ringan (misalnya larutan bleach 2–5%) untuk mencegah penyebaran penyakit.",
                icon = R.drawable.ic_transform
            ),
            TipsList(
                title = "Kualitas Air yang Baik",
                desc = "Gunakan air bersih dan bebas kontaminan. Ganti larutan nutrisi secara berkala (1–2 minggu sekali) untuk mencegah perkembangan mikroorganisme berbahaya.",
                icon = R.drawable.ic_weather
            ),
            TipsList(
                title = "Pengaturan Nutrisi dan pH Stabil",
                desc = "Jaga pH larutan pada kisaran 5.5–6.5 dan EC sesuai fase pertumbuhan. Ketidakseimbangan nutrisi bisa melemahkan tanaman dan memicu serangan penyakit.",
                icon = R.drawable.ic_weather
            ),
            TipsList(
                title = "Pisahkan Tanaman Sakit",
                desc = "Jika ada tanaman yang menunjukkan gejala penyakit, segera pisahkan agar tidak menyebar ke tanaman lainnya.",
                icon = R.drawable.ic_weather
            ),
        )
    }
}