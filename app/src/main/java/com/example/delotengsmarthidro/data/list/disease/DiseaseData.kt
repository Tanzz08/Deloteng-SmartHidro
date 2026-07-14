package com.example.delotengsmarthidro.data.list.disease

object DiseaseData {

    private val diseaseList = listOf(
        Disease(
            modelKey = "bercak_daun",
            displayName = "Bercak Daun (Crescopora/Septoria)",
            severity = "Waspada (Menengah) 🟡",
            severityExplanation = "Penyakit ini menurunkan kualitas visual dan harga jual daun selada. Jika dibiarkan, bercak akan menghambat fotosintesis, namun tanaman biasanya masih bisa bertahan hidup jika segera ditangani.",
            causes = listOf(
                "Infeksi jamur (seperti Cercospora) atau bakteri patogen.",
                "Kelembapan udara di sekitar daun terlalu tinggi karena sirkulasi udara buruk.",
                "Adanya percikan air nutrisi yang mengenai daun dan dibiarkan basah dalam waktu lama."
            ),
            characteristics = listOf(
                "Terdapat bercak-bercak melingkar berwarna cokelat, abu-abu, atau kehitaman pada daun.",
                "Biasanya terdapat lingkaran kuning yang mengelilingi bercak tersebut.",
                "Pada kondisi parah, bagian tengah bercak mengering dan berlubang (seperti mata kodok)."
            ),
            solution = listOf(
                "<b>Sanitasi Mekanis:</b> Segera potong dan musnahkan helaian daun yang terinfeksi. Jangan membuang sisa daun di sekitar instalasi hidroponik agar spora tidak menyebar.",
                "<b>Modifikasi Lingkungan:</b> Renggangkan jarak antar netpot untuk menurunkan tingkat kelembapan (RH) dan memperbaiki sirkulasi udara di kanopi tanaman.",
                "<b>Pengendalian Nabati/Kimia:</b> Semprotkan fungisida nabati (minyak mimba/neem oil) atau fungisida berbahan aktif tembaga (Mancozeb) pada dosis anjuran, aplikasikan pada sore hari."
            )
        ),
        Disease(
            modelKey = "embun_bulu",
            displayName = "Embun Bulu (Bremia lactucae)",
            severity = "Berbahaya (Tinggi) 🟠",
            severityExplanation = "Sangat menular! Spora jamur ini sangat mudah diterbangkan angin dan dapat menyebar ke seluruh kanopi instalasi hidroponik dalam hitungan hari. Segera isolasi tanaman yang terinfeksi.",
            causes = listOf(
                "Patogen Bremia lactucae yang terbawa angin atau genangan air.",
                "Suhu udara sejuk/dingin yang disertai embun tebal di pagi hari.",
                "Lingkungan instalasi hidroponik yang terlalu rapat dan basah."
            ),
            characteristics = listOf(
                "Bercak kuning memucat yang bentuknya bersudut-sudut di permukaan atas daun.",
                "Terdapat spora halus seperti kapas atau bulu berwarna putih keabu-abuan di bagian BAWAH daun.",
                "Daun perlahan mengerut dan mati dari pinggir."
            ),
            solution = listOf(
                "<b>Eradikasi Bertahap:</b> Buang daun-daun tua di bagian bawah yang berbatasan langsung dengan tandon, karena area ini paling lembap dan rentan.",
                "<b>Kontrol Kelembapan:</b> Pindahkan baki hidroponik ke tempat yang memiliki ventilasi silang yang baik dan pastikan tanaman terkena sinar matahari penuh di pagi hari untuk mengeringkan embun.",
                "<b>Aplikasi Agen Hayati:</b> Gunakan pestisida hayati (seperti Trichoderma spp.) atau fungisida sistemik berbahan aktif propamokarb hidroklorida jika tingkat serangan sudah di atas ambang ekonomi."
            )
        ),
        Disease(
            modelKey = "healthy",
            displayName = "Tanaman Sehat",
            severity = "Aman 🟢",
            severityExplanation = "Kondisi tanaman optimal. Bebas dari infeksi patogen jamur/bakteri serta tidak menunjukkan indikasi defisiensi nutrisi.",
            causes = listOf(),
            characteristics = listOf(
                "Daun berwarna hijau cerah, segar, tegak, dan renyah.",
                "Akar berwarna putih bersih seperti tauge, menembus kain flanel dan menjuntai ke air nutrisi.",
                "Tidak ada gradasi kuning yang tidak wajar atau tepi daun yang gosong (tip burn)."
            ),
            solution = listOf(
                "<b>Jaga Level Air:</b> Selalu sisakan 'Ruang Udara' (air gap) sekitar 2-3 cm antara permukaan air dan dasar netpot agar akar napas tetap berfungsi.",
                "<b>Aduk Manual:</b> Aduk air di bak nutrisi secara manual setiap pagi/sore untuk menjaga suplai oksigen terlarut (Dissolved Oxygen).",
                "<b>Cek Rutin:</b> Pantau ppm dan pH setiap 3 hari sekali. Tambahkan air baku jika penguapan tinggi akibat cuaca panas."
            )
        ),
        Disease(
            modelKey = "embun_tepung",
            displayName = "Embun Tepung (Golovinomyces cichoracearum)",
            severity = "Waspada (Menengah) 🟡",
            severityExplanation = "Lapisan jamur menghalangi cahaya matahari sehingga menghambat fotosintesis. Hal ini membuat bobot panen menurun dan selada menjadi kerdil, namun tidak langsung mematikan tanaman.",
            causes = listOf(
                "Infeksi jamur Golovinomyces cichoracearum.",
                "Kondisi lingkungan kering namun rindang/kurang mendapat sinar matahari langsung.",
                "Sirkulasi udara yang pengap (stagnan) di sekitar area penanaman."
            ),
            characteristics = listOf(
                "Terdapat lapisan serbuk putih seperti bedak tabur yang menutupi permukaan ATAS daun.",
                "Tanaman terlihat kusam, daun perlahan menguning dan mengerut.",
                "Pertumbuhan selada menjadi kerdil atau lambat."
            ),
            solution = listOf(
                "<b>Manipulasi Pencahayaan:</b> Pindahkan baki ke area terbuka yang terpapar sinar matahari langsung, karena spora jamur ini sangat peka terhadap radiasi UV dan panas.",
                "<b>Pengendalian Biorasional:</b> Semprotkan larutan Kalium Bikarbonat (baking soda) dengan konsentrasi 0.5% - 1% dicampur sedikit perekat non-deterjen. Sulfur (belerang) juga sangat efektif.",
                "<b>Penjarangan Kanopi:</b> Pangkas daun yang terinfeksi parah agar serbuk spora tidak menular ke tanaman lain melalui hembusan angin."
            )
        ),
        Disease(
            modelKey = "layu_busuk_akar",
            displayName = "Layu / Gejala Busuk Akar",
            severity = "Kritis 🔴 (Jika Akar Busuk) / Waspada 🟡 (Jika Akar Sehat)",
            severityExplanation = "Diagnosis Ganda: Jika akar membusuk, ini sangat mematikan karena patogen menyebar lewat air nutrisi di tandon. Jika akar masih putih, tanaman hanya mengalami stres panas/nutrisi dan bisa pulih dengan cepat.",
            causes = listOf(
                "Kondisi 1 (Busuk Akar): Suhu air nutrisi terlalu panas (>28°C) dan minim oksigen terlarut, memicu patogen Pythium menyerang akar.",
                "Kondisi 2 (Defisiensi Nutrisi/Stres): Kadar PPM AB Mix terlalu rendah, suhu lingkungan terlalu panas, atau pH air melenceng (>6.5 atau <5.5) sehingga nutrisi tidak bisa diserap walaupun akar sehat."
            ),
            characteristics = listOf(
                "Daun tampak lemas, layu, lunglai, atau memudar menjadi kuning mulai dari daun bagian bawah.",
                "<b>PANDUAN CEK AKAR (WAJIB DILAKUKAN):</b> Angkat netpot dan periksa kondisi akar di bawah kain flanel untuk menentukan tingkat bahaya sebenarnya."
            ),
            solution = listOf(
                "<b>DIAGNOSIS A - JIKA AKAR BERWARNA PUTIH & SEHAT (Tingkat: Waspada):</b> Tanaman hanya mengalami stres panas atau kurang nutrisi. <b>Solusi:</b> Periksa kadar PPM nutrisi (sesuaikan ke 600-1000 PPM untuk selada) dan stabilkan pH ke 5.5 - 6.5. Tanaman akan segar kembali.",
                "<b>DIAGNOSIS B - JIKA AKAR COKELAT, BERLENDIR & BAU (Tingkat: Kritis):</b> Ini adalah Busuk Akar yang menular! <b>Solusi:</b> Segera kuras total bak tandon, sterilisasi bak dengan larutan Hidrogen Peroksida (H2O2 3%) atau sabun. Gunting bagian akar tanaman yang busuk (sisakan yang putih).",
                "<b>Pencegahan Lanjutan:</b> Tambahkan celah udara (air gap) minimal 3 cm antara permukaan air dan dasar netpot agar akar napas tetap berfungsi, serta tutupi bak tandon agar tidak langsung terkena terik matahari."
            )
        ),
        Disease(
            modelKey = "bukan_daun_selada",
            displayName = "Bukan Daun Selada",
            severity = "Tidak Relevan ⚪",
            severityExplanation = "",
            causes = listOf(),
            characteristics = listOf(),
            solution = listOf()
        )
    )

    fun findByLabel(label: String): Disease? {
        val cleanLabel = label.trim()
        return diseaseList.find {
            it.modelKey.equals(cleanLabel, ignoreCase = true)
        }
    }
}