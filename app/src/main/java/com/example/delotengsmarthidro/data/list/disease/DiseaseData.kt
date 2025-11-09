package com.example.delotengsmarthidro.data

import com.example.delotengsmarthidro.data.list.disease.Disease

object DiseaseData {

    // Daftar semua penyakit yang bisa dideteksi model Anda
    private val diseaseList = listOf(
        Disease(
            modelKey = "bacterial",
            displayName = "Bacterial Spot (Bercak Bakteri)",
            characteristics = listOf(
                "Ciri utamanya adalah bercak kecil, basah, dan berwarna gelap (hitam atau coklat tua) pada daun. Bercak ini seringkali memiliki lingkaran kuning (halo) di sekitarnya.",
                "Bercak dapat membesar dan menyatu, menyebabkan area daun yang lebih luas menjadi rusak dan berlubang. Dalam kondisi lembab, bercak mungkin terlihat berminyak."
            ),
            solution = listOf(
                "<b>Jaga Kebersihan:</b> Buang dan hancurkan daun atau tanaman yang terinfeksi untuk mengurangi penyebaran.",
                "<b>Jaga Daun Tetap Kering:</b> Hindari penyiraman dari atas yang membasahi daun. Siram langsung ke media tanam. Bakteri menyebar melalui percikan air.",
                "<b>Sirkulasi Udara:</b> Pastikan sirkulasi udara di sekitar tanaman baik untuk mempercepat pengeringan daun.",
                "<b>Bakterisida:</b> Jika serangan parah, gunakan bakterisida (obat anti bakteri) yang berbahan dasar tembaga. Selalu ikuti petunjuk pada kemasan."
            )
        ),
        Disease(
            modelKey = "healthy",
            displayName = "Sehat",
            characteristics = listOf(
                "Daun tampak hijau segar, cerah, dan utuh. Tidak ada bercak, perubahan warna yang aneh (selain variasi normal), atau tanda-tanda kerusakan.",
                "Pertumbuhan tanaman tampak normal dan kokoh."
            ),
            solution = listOf(
                "Kerja bagus! Tanaman Anda dalam kondisi optimal.",
                "<b>Pertahankan:</b> Lanjutkan rutinitas penyiraman, pemberian nutrisi, dan pencahayaan yang sudah baik.",
                "<b>Pencegahan:</b> Tetap lakukan inspeksi rutin untuk mendeteksi masalah sejak dini."
            )
        ),
        Disease(
            modelKey = "downy",
            displayName = "Late Blight (Hawar Daun)",
            characteristics = listOf(
                "Penyakit ini sangat cepat menyebar. Gejala awal adalah bercak besar, tidak beraturan, berwarna hijau pucat atau kebasahan pada daun, sering dimulai dari tepi atau ujung daun.",
                "Dalam cuaca lembab, lapisan jamur berbulu putih atau abu-abu akan muncul di bagian bawah daun.",
                "Bercak dengan cepat berubah menjadi coklat tua atau hitam, dan seluruh daun bisa mati dan mengering dalam beberapa hari."
            ),
            solution = listOf(
                "<b>Tindakan Cepat:</b> Penyakit ini menyebar sangat cepat. Segera buang dan musnahkan (bakar atau kubur dalam) semua bagian yang terinfeksi.",
                "<b>Jaga Kelembaban:</b> Kurangi kelembaban di sekitar tanaman. Beri jarak antar tanaman dan pastikan sirkulasi udara maksimal.",
                "<b>Fungisida:</b> Diperlukan aplikasi fungisida preventif atau kuratif. Cari produk yang efektif untuk *Phytophthora infestans* (organisme penyebab Late Blight)."
            )
        ),
        Disease(
            modelKey = "powdery",
            displayName = "Powdery Mildew (Embun Tepung)",
            characteristics = listOf(
                "Gejala paling khas adalah munculnya lapisan seperti tepung atau bedak berwarna putih keabu-abuan di permukaan atas daun, batang, dan bunga.",
                "Awalnya mungkin hanya bercak-bercak kecil, tapi bisa dengan cepat menyebar menutupi seluruh permukaan daun.",
                "Daun yang terinfeksi parah bisa menguning, menjadi kering, keriput, dan akhirnya rontok. Pertumbuhan tanaman bisa terhambat."
            ),
            solution = listOf(
                "<b>Sirkulasi Udara:</b> Tingkatkan sirkulasi udara di sekitar tanaman. Jangan menanam terlalu rapat.",
                "<b>Kurangi Kelembaban:</b> Jaga kelembaban lingkungan agar tidak terlalu tinggi, terutama di malam hari.",
                "<b>Fungisida:</b> Gunakan fungisida yang dirancang untuk Powdery Mildew. Opsi organik termasuk minyak nimba (neem oil) atau kalium bikarbonat. Opsi kimia juga tersedia.",
                "<b>Sanitasi:</b> Buang daun yang terinfeksi parah untuk mengurangi sumber spora."
            )
        ),
        Disease(
            modelKey = "septoria",
            displayName = "Septoria (Bercak Daun)",
            characteristics = listOf(
                "Ciri utamanya adalah munculnya banyak bercak kecil (diameter 1-3 mm) yang bundar pada daun.",
                "Bercak ini awalnya basah, lalu berkembang menjadi berwarna coklat keabu-abuan di bagian tengah dengan tepi berwarna coklat tua atau ungu.",
                "Di tengah bercak sering terlihat titik-titik hitam kecil (disebut piknidia), yang merupakan badan buah jamur.",
                "Penyakit ini biasanya dimulai dari daun-daun yang lebih tua di bagian bawah tanaman dan perlahan-lahan menyebar ke atas."
            ),
            solution = listOf(
                "<b>Sanitasi:</b> Segera buang dan musnahkan daun atau bagian tanaman yang terinfeksi. Jangan biarkan sisa-sisa tanaman menumpuk di area tanam.",
                "<b>Sirkulasi Udara:</b> Pastikan ada sirkulasi udara yang baik di sekitar tanaman.",
                "<b>Penyiraman:</b> Hindari membasahi daun saat menyiram. Siram langsung ke media tanam. Jamur Septoria menyebar melalui percikan air.",
                "<b>Fungisida (jika diperlukan):</b> Gunakan fungisida yang mengandung bahan aktif seperti klorotalonil, mancozeb, atau fungisida berbahan dasar tembaga."
            )
        ),
        Disease(
            modelKey = "wilt",
            displayName = "Wilt (Layu)",
            characteristics = listOf(
                "Gejala utamanya adalah tanaman menjadi layu, seringkali dimulai dari satu sisi atau cabang. Daun bagian bawah akan menguning, kemudian coklat dan mati, lalu bergerak ke atas.",
                "Tanaman mungkin terlihat layu di siang hari yang panas dan tampak sedikit pulih di pagi atau malam hari, tetapi akhirnya akan layu permanen.",
                "Jika batang dipotong, mungkin terlihat perubahan warna (garis atau cincin coklat) pada jaringan vaskular (pembuluh) di dalamnya. Ini adalah tanda Layu Fusarium atau Verticillium."
            ),
            solution = listOf(
                "<b>Tidak Ada Obat:</b> Penyakit layu vaskular (Fusarium, Verticillium) yang disebabkan oleh jamur dari tanah sangat sulit atau tidak bisa diobati.",
                "<b>Cabut & Musnahkan:</b> Segera cabut seluruh tanaman yang terinfeksi (termasuk akarnya) dan musnahkan. Jangan tanam tanaman sejenis di lokasi yang sama.",
                "<b>Sterilisasi:</b> Jika menggunakan pot atau media tanam hidroponik, sterilisasi semua peralatan dan ganti media tanam sepenuhnya.",
                "<b>Pencegahan:</b> Gunakan varietas tanaman yang tahan (resisten) terhadap penyakit layu."
            )
        ),
        Disease(
            modelKey = "viral",
            displayName = "Viral (Virus)",
            characteristics = listOf(
                "Gejala virus sangat bervariasi, tetapi yang umum adalah pola mosaik (belang-belang hijau muda dan hijau tua) pada daun.",
                "Gejala lain termasuk daun menguning, keriput, kerdil, atau memiliki bentuk yang tidak normal (malformasi).",
                "Pertumbuhan tanaman secara keseluruhan akan terhambat atau kerdil. Produksi buah (jika ada) akan menurun drastis."
            ),
            solution = listOf(
                "<b>Tidak Ada Obat:</b> Tidak ada obat untuk menyembuhkan tanaman yang sudah terinfeksi virus.",
                "<b>Cabut & Musnahkan:</b> Segera cabut dan musnahkan tanaman yang terinfeksi untuk mencegah penyebaran ke tanaman sehat lainnya.",
                "<b>Kendalikan Vektor:</b> Virus sering disebarkan oleh serangga (vektor) seperti kutu daun (aphid) atau thrips. Kendalikan populasi serangga ini.",
                "<b>Kebersihan:</b> Selalu bersihkan alat-alat (gunting, pisau) dengan disinfektan saat berpindah dari satu tanaman ke tanaman lain."
            )
        )
    )

    fun findByLabel(label: String): Disease? {
        // .find akan mencari item pertama yang cocok
        // ignoreCase = true penting agar "Bacterial Spot" sama dengan "bacterial spot"
        return diseaseList.find { it.modelKey.equals(label, ignoreCase = true) }
    }
}