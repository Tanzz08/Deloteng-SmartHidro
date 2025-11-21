package com.example.delotengsmarthidro.data.list.tips

import com.example.delotengsmarthidro.R

object DummyTipsData {
    fun getTipsSolution(): List<TipsList> {
        return listOf(
            TipsList(
                title = "Aturan Celah Udara (Air Gap)",
                desc = "Kunci sukses wick: JANGAN isi air sampai penuh. Sisakan ruang 2-3 cm di bawah netpot. Akar bagian atas butuh bernapas langsung dari udara, akar bawah mencari air.",
                icon = R.drawable.ic_weather
            ),
            TipsList(
                title = "Aduk Nutrisi Setiap Pagi",
                desc = "Sistem wick tidak punya pompa. Wajib mengaduk air di bak setiap pagi secara manual. Ini memasukkan gelembung oksigen agar akar tidak busuk dan nutrisi tidak mengendap.",
                icon = R.drawable.ic_transform
            ),
            TipsList(
                title = "Cegah Cahaya Masuk (Anti Lumut)",
                desc = "Pastikan bak penampung TIDAK tembus cahaya. Cahaya yang masuk ke air menyebabkan lumut tumbuh cepat. Lumut akan mencuri oksigen dan nutrisi tanaman Anda.",
                icon = R.drawable.ic_bug
            ),
            TipsList(
                title = "Waspada Jentik Nyamuk",
                desc = "Air diam adalah surga nyamuk. Tutup rapat celah di sekitar netpot/tutup baki. Cek rutin seminggu sekali, jentik nyamuk bisa membawa penyakit ke lingkungan rumah.",
                icon = R.drawable.ic_danger
            ),
            TipsList(
                title = "Perawatan Kain Flanel (Sumbu)",
                desc = "Pastikan kain flanel menyentuh dasar air. Jika menggunakan flanel bekas, WAJIB direbus atau direndam air panas dulu untuk membunuh jamur dari tanaman sebelumnya.",
                icon = R.drawable.ic_tabler_seeding
            ),
            TipsList(
                title = "Jaga Suhu Air Tetap Sejuk",
                desc = "Air hangat mengandung sedikit oksigen. Jangan letakkan bak wick di lantai beton yang panas atau kena matahari siang langsung. Alasi bak dengan kayu/styrofoam.",
                icon = R.drawable.wind
            ),
        )
    }
}