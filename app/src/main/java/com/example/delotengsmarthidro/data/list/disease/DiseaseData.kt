package com.example.delotengsmarthidro.data.list.disease

object DiseaseData {

    private val diseaseList = listOf(
        Disease(
            modelKey = "bacterial",
            displayName = "Busuk Lunak (Bakteri)",
            characteristics = listOf(
                "Pangkal batang (yang menempel di rockwool/sumbu) menjadi lembek, berlendir, dan bau busuk.",
                "Daun terlihat seperti tersiram air panas dan lonyot.",
                "Sering terjadi jika sumbu terlalu basah sampai membanjiri batang tanaman."
            ),
            solution = listOf(
                "<b>Turunkan Level Air:</b> Jangan isi bak nutrisi sampai penuh menyentuh netpot. Sisakan jarak (ruang udara) minimal 2-3 cm di bawah netpot.",
                "<b>Sumbu Jangan Mencekik:</b> Pastikan kain flanel/sumbu tidak melilit batang terlalu kencang, biarkan longgar agar batang tidak lembap terus menerus.",
                "<b>Buang Tanaman:</b> Jika sudah berlendir, segera buang tanaman dan cuci bersih netpotnya."
            )
        ),
        Disease(
            modelKey = "downy",
            displayName = "Embun Bulu (Downy Mildew)",
            characteristics = listOf(
                "Bercak kuning kotak-kotak di permukaan atas daun.",
                "Ada spora putih seperti kapas di bawah daun.",
                "Sering menyerang sistem wick yang diletakkan di tempat lembap/kurang angin."
            ),
            solution = listOf(
                "<b>Cari Angin:</b> Sistem wick tidak punya pompa, jadi sirkulasi udara bergantung pada angin sekitar. Pindahkan baki ke tempat yang lebih berangin.",
                "<b>Jemur Pagi:</b> Pastikan tanaman terkena sinar matahari pagi minimal 3-4 jam untuk membunuh jamur.",
                "<b>Kurangi Populasi:</b> Jika satu baki terlalu padat, kurangi jumlah tanaman agar daun tidak saling menempel."
            )
        ),
        Disease(
            modelKey = "healthy",
            displayName = "Tanaman Sehat",
            characteristics = listOf(
                "Daun segar, tegak, dan berwarna cerah.",
                "Ciri Khas Wick Sehat: Akar putih bersih menembus kain flanel dan menjuntai ke air nutrisi.",
                "Tidak ada jentik nyamuk di dalam bak penampungan nutrisi."
            ),
            solution = listOf(
                "<b>Jaga Level Air:</b> Selalu sisakan 'Ruang Udara' antara permukaan air dan dasar netpot. Akar butuh napas!",
                "<b>Aduk Nutrisi:</b> Karena airnya diam, aduk air di bak nutrisi secara manual setiap pagi agar oksigen terlarut bertambah.",
                "<b>Cek Pekatan:</b> Tambahkan air baku jika level air turun, jangan biarkan akar kering gantung."
            )
        ),
        Disease(
            modelKey = "powdery",
            displayName = "Embun Tepung (Powdery Mildew)",
            characteristics = listOf(
                "Lapisan putih seperti bedak tabur menutupi daun.",
                "Tanaman terlihat kusam dan lambat tumbuh.",
                "Biasanya muncul saat cuaca panas tapi tempatnya teduh/pengap."
            ),
            solution = listOf(
                "<b>Sinar Matahari:</b> Jamur ini benci matahari. Geser baki hidroponik ke area yang lebih terang.",
                "<b>Semprot Susu:</b> Semprotkan campuran air dan susu murni (9:1) atau larutan baking soda tipis-tipis ke daun.",
                "<b>Pangkas Daun:</b> Buang daun yang tertutup bedak putih parah agar tidak menyebar ke sebelah."
            )
        ),
        Disease(
            modelKey = "septoria",
            displayName = "Bercak Septoria",
            characteristics = listOf(
                "Bercak bulat coklat dengan titik hitam kecil di tengahnya.",
                "Biasanya muncul karena wadah/baki wick kotor bekas tanam sebelumnya.",
                "Menyerang daun bawah dulu."
            ),
            solution = listOf(
                "<b>Kebersihan Wadah:</b> Saat ganti tanam, sikat bersih baki penampung air. Lumut dan sisa akar lama adalah sarang penyakit.",
                "<b>Buang Daun Sakit:</b> Gunting daun yang bercak dan buang jauh-jauh.",
                "<b>Jaga Kebersihan:</b> Jangan biarkan daun kering jatuh dan mengapung di air nutrisi."
            )
        ),
        Disease(
            modelKey = "viral",
            displayName = "Virus (Keriting/Mozaik)",
            characteristics = listOf(
                "Daun keriting, kerdil, atau belang-belang kuning.",
                "Disebabkan oleh serangga yang hinggap di tanaman.",
                "Pertumbuhan macet total."
            ),
            solution = listOf(
                "<b>Cabut Segera:</b> Tidak ada obat. Cabut tanaman dan buang.",
                "<b>Cek Lingkungan:</b> Bersihkan rumput liar di sekitar rak hidroponik.",
                "<b>Perangkap Kuning:</b> Gantungkan lem lalat/perangkap kuning di dekat baki untuk menangkap serangga pembawa virus."
            )
        ),
        Disease(
            modelKey = "wilt",
            displayName = "Busuk Akar (Layu)",
            characteristics = listOf(
                "Tanaman layu walau air nutrisi masih penuh.",
                "Cek Akar & Sumbu: Akar berubah warna jadi coklat/hitam, lembek, dan baunya tidak sedap. Kain flanel berlendir.",
                "Penyebab utamanya adalah air yang panas dan kurang oksigen (karena air diam)."
            ),
            solution = listOf(
                "<b>Kuras Bak:</b> Segera buang air nutrisi yang lama, cuci baki sampai bersih dari lendir.",
                "<b>Aduk Manual:</b> Wajib mengaduk air nutrisi setiap hari (pagi & sore) pakai tangan/gayung untuk memasukkan oksigen (karena tidak pakai pompa).",
                "<b>Ruang Udara (Air Gap):</b> Saat isi ulang air, JANGAN SAMPAI PENUH. Sisakan celah udara 2-3 cm di bawah netpot agar akar bagian atas bisa bernapas langsung dari udara.",
                "<b>Tutup Baki:</b> Pastikan baki tertutup rapat (tidak tembus cahaya) agar suhu air tidak cepat panas dan lumut tidak tumbuh."
            )
        )
    )

    fun findByLabel(label: String): Disease? {
        val cleanLabel = label.trim()
        return diseaseList.find {
            it.modelKey.equals(cleanLabel, ignoreCase = true)
        }
    }
}