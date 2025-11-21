package com.example.delotengsmarthidro.data.list.tutorial

import com.example.delotengsmarthidro.R
import com.example.delotengsmarthidro.data.database.TutorialEntity

object DummyTutorialData {
    fun getTutorialSteps(): List<TutorialEntity> {
        return listOf(
            TutorialEntity(
                title = "1. Persiapan Alat dan Bahan",
                desc = "Siapkan benih, rockwool.jpg atau media semai, netpot, wadah nutrisi, dan nutrisi hidroponik. Gunakan barang bekas seperti botol atau ember jika perlu.",
                icon = R.drawable.ic_truc,
                detail = """<h1>Alat dan Bahan</h1>
    <p>Untuk memulai penanaman hidroponik sistem wick, Anda memerlukan alat dan bahan berikut:</p>
            <h3>Daftar Bahan Utama</h3>
            <ul>
                <li>
                    <b>Rockwool/Busa</b>: Media tanam awal untuk menyemai benih
                    <div class="img"><img src="file:///android_asset/rockwool.jpg.jpg" style="height: 200px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Nutrisi AB Mix</b>: Nutrisi utama yang akan dilarutkan dalam air
                    <div class="img"><img src="file:///android_asset/mix.jpg" alt="" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Air Bersih</b>: Digunakan sebagai pelarut nutrisi AB Mix
                </li>
            </ul>
            <h3>Daftar Alat Utama</h3>
            <ul>
                <li>
                    <b>Kotak Styrofoam</b>: Berfungsi sebagai bak penampung Styrofoam
                    <div class="img"><img src="file:///android_asset/styrofoam.png" alt=""></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Pot Plastik (Netpot)</b>: Wadah untuk menanam bibit
                    <div class="img"><img src="file:///android_asset/netpot.jpg" alt="" style="height: 200px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Kain Flanel/Sumbu</b>: Berfungsi sebagai sumbu (wick) untuk mengalirkan nutrisi
                    <div class="img"><img src="file:///android_asset/sumbu.jpg" alt="" style="height: 160px;"> <img src="img/flanel.jpg" alt="" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Botol Plastik (Opsional)</b>: Dapat dimanfaatkan sebagai pengganti pot atau wadah
                    <div class="img"><img src="file:///android_asset/botol.jpg" alt=""></div>
                </li>
            </ul>"""
            ),
            TutorialEntity(
                title = "2. Penyemaian",
                desc = "Basahi rockwool.jpg, masukkan 1-2 benih per lubang, tutup tipis. Simpan di tempat teduh, semprot pagi & sore. Bibit muncul 1-7 hari.",
                icon = R.drawable.ic_tabler_seeding,
                detail = """<h1>Alat dan Bahan</h1>
    <p>Untuk memulai penanaman hidroponik sistem wick, Anda memerlukan alat dan bahan berikut:</p>
    <ol>
        <li>
            <h3>Daftar Bahan Utama</h3>
            <ul>
                <li>
                    <b>Rockwool/Busa</b>: Media tanam awal untuk menyemai benih
                    <div class="img"><img src="img/rockwool.jpg.jpg" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Nutrisi AB Mix</b>: Nutrisi utama yang akan dilarutkan dalam air
                    <div class="img"><img src="img/mix.jpg" alt="" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Air Bersih</b>: Digunakan sebagai pelarut nutrisi AB Mix
                    <div class="img"><img src="" alt=""></div>
                </li>
            </ul>
        </li>
        <li>
            <h3>Daftar Alat Utama</h3>
            <ul>
                <li>
                    <b>Kotak Styrofoam</b>: Berfungsi sebagai bak penampung Styrofoam
                    <div class="img"><img src="img/styrofoam.png" alt=""></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Pot Plastik (Netpot)</b>: Wadah untuk menanam bibit
                    <div class="img"><img src="img/netpot.jpg" alt="" style="height: 200px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Kain Flanel/Sumbu</b>: Berfungsi sebagai sumbu (wick) untuk mengalirkan nutrisi
                    <div class="img"><img src="img/sumbu.jpg" alt="" style="height: 160px;"> <img src="img/flanel.jpg" alt="" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Botol Plastik (Opsional)</b>: Dapat dimanfaatkan sebagai pengganti pot atau wadah
                    <div class="img"><img src="" alt=""></div>
                </li>
            </ul>
        </li>
    </ol>"""
            ),
            TutorialEntity(
                title = "3. Transplant & Setup",
                desc = "Pindahkan bibit saat 2-4 daun sejati. Pasang netpot di rakit atau sistem wick sehingga akar menyentuh larutan nutrisi.",
                icon = R.drawable.ic_transform,
                detail = """<h1>Alat dan Bahan</h1>
    <p>Untuk memulai penanaman hidroponik sistem wick, Anda memerlukan alat dan bahan berikut:</p>
    <ol>
        <li>
            <h3>Daftar Bahan Utama</h3>
            <ul>
                <li>
                    <b>Rockwool/Busa</b>: Media tanam awal untuk menyemai benih
                    <div class="img"><img src="img/rockwool.jpg.jpg" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Nutrisi AB Mix</b>: Nutrisi utama yang akan dilarutkan dalam air
                    <div class="img"><img src="img/mix.jpg" alt="" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Air Bersih</b>: Digunakan sebagai pelarut nutrisi AB Mix
                    <div class="img"><img src="" alt=""></div>
                </li>
            </ul>
        </li>
        <li>
            <h3>Daftar Alat Utama</h3>
            <ul>
                <li>
                    <b>Kotak Styrofoam</b>: Berfungsi sebagai bak penampung Styrofoam
                    <div class="img"><img src="file:///android_asset/styrofoam.png" alt=""></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Pot Plastik (Netpot)</b>: Wadah untuk menanam bibit
                    <div class="img"><img src="img/netpot.jpg" alt="" style="height: 200px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Kain Flanel/Sumbu</b>: Berfungsi sebagai sumbu (wick) untuk mengalirkan nutrisi
                    <div class="img"><img src="img/sumbu.jpg" alt="" style="height: 160px;"> <img src="img/flanel.jpg" alt="" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Botol Plastik (Opsional)</b>: Dapat dimanfaatkan sebagai pengganti pot atau wadah
                    <div class="img"><img src="" alt=""></div>
                </li>
            </ul>
        </li>
    </ol>"""
            ),
            TutorialEntity(
                title = "4. Perawatan Rutin",
                desc = "Cek larutan nutrisi setiap 2-3 hari, jaga ketinggian air. Beri sinar 4-6 jam/hari. Ganti larutan penuh setiap 7-10 hari bila perlu.",
                icon = R.drawable.ic_weather,
                detail = """<h1>Alat dan Bahan</h1>
    <p>Untuk memulai penanaman hidroponik sistem wick, Anda memerlukan alat dan bahan berikut:</p>
    <ol>
        <li>
            <h3>Daftar Bahan Utama</h3>
            <ul>
                <li>
                    <b>Rockwool/Busa</b>: Media tanam awal untuk menyemai benih
                    <div class="img"><img src="img/rockwool.jpg.jpg" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Nutrisi AB Mix</b>: Nutrisi utama yang akan dilarutkan dalam air
                    <div class="img"><img src="img/mix.jpg" alt="" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Air Bersih</b>: Digunakan sebagai pelarut nutrisi AB Mix
                    <div class="img"><img src="" alt=""></div>
                </li>
            </ul>
        </li>
        <li>
            <h3>Daftar Alat Utama</h3>
            <ul>
                <li>
                    <b>Kotak Styrofoam</b>: Berfungsi sebagai bak penampung Styrofoam
                    <div class="img"><img src="img/styrofoam.png" alt=""></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Pot Plastik (Netpot)</b>: Wadah untuk menanam bibit
                    <div class="img"><img src="img/netpot.jpg" alt="" style="height: 200px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Kain Flanel/Sumbu</b>: Berfungsi sebagai sumbu (wick) untuk mengalirkan nutrisi
                    <div class="img"><img src="img/sumbu.jpg" alt="" style="height: 160px;"> <img src="img/flanel.jpg" alt="" style="height: 160px;"></div>
                </li>
            </ul>
            <ul>
                <li>
                    <b>Botol Plastik (Opsional)</b>: Dapat dimanfaatkan sebagai pengganti pot atau wadah
                    <div class="img"><img src="" alt=""></div>
                </li>
            </ul>
        </li>
    </ol>"""
            ),
            TutorialEntity(
                title = "5. Perawatan Rutin",
                desc = "Cek larutan nutrisi setiap 2-3 hari, jaga ketinggian air. Beri sinar 4-6 jam/hari. Ganti larutan penuh setiap 7-10 hari bila perlu.",
                icon = R.drawable.ic_weather,
                detail = """<h1>Alat dan Bahan</h1>
                            <p>Untuk memulai penanaman hidroponik sistem wick, Anda memerlukan alat dan bahan berikut:</p>
                            <ol>
                                <li>
                                    <h3>Daftar Bahan Utama</h3>
                                    <ul>
                                        <li>
                                            <b>Rockwool/Busa</b>: Media tanam awal untuk menyemai benih
                                            <div class="img"><img src="img/rockwool.jpg.jpg" style="height: 160px;"></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Nutrisi AB Mix</b>: Nutrisi utama yang akan dilarutkan dalam air
                                            <div class="img"><img src="img/mix.jpg" alt="" style="height: 160px;"></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Air Bersih</b>: Digunakan sebagai pelarut nutrisi AB Mix
                                            <div class="img"><img src="" alt=""></div>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <h3>Daftar Alat Utama</h3>
                                    <ul>
                                        <li>
                                            <b>Kotak Styrofoam</b>: Berfungsi sebagai bak penampung Styrofoam
                                            <div class="img"><img src="img/styrofoam.png" alt=""></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Pot Plastik (Netpot)</b>: Wadah untuk menanam bibit
                                            <div class="img"><img src="img/netpot.jpg" alt="" style="height: 200px;"></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Kain Flanel/Sumbu</b>: Berfungsi sebagai sumbu (wick) untuk mengalirkan nutrisi
                                            <div class="img"><img src="img/sumbu.jpg" alt="" style="height: 160px;"> <img src="img/flanel.jpg" alt="" style="height: 160px;"></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Botol Plastik (Opsional)</b>: Dapat dimanfaatkan sebagai pengganti pot atau wadah
                                            <div class="img"><img src="" alt=""></div>
                                        </li>
                                    </ul>
                                </li>
                            </ol>"""
                                    ),
                                    TutorialEntity(
                                        title = "6. Perawatan Rutin",
                                        desc = "Cek larutan nutrisi setiap 2-3 hari, jaga ketinggian air. Beri sinar 4-6 jam/hari. Ganti larutan penuh setiap 7-10 hari bila perlu.",
                                        icon = R.drawable.ic_weather,
                                        detail = """<h1>Alat dan Bahan</h1>
                            <p>Untuk memulai penanaman hidroponik sistem wick, Anda memerlukan alat dan bahan berikut:</p>
                            <ol>
                                <li>
                                    <h3>Daftar Bahan Utama</h3>
                                    <ul>
                                        <li>
                                            <b>Rockwool/Busa</b>: Media tanam awal untuk menyemai benih
                                            <div class="img"><img src="img/rockwool.jpg.jpg" style="height: 160px;"></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Nutrisi AB Mix</b>: Nutrisi utama yang akan dilarutkan dalam air
                                            <div class="img"><img src="img/mix.jpg" alt="" style="height: 160px;"></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Air Bersih</b>: Digunakan sebagai pelarut nutrisi AB Mix
                                            <div class="img"><img src="" alt=""></div>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <h3>Daftar Alat Utama</h3>
                                    <ul>
                                        <li>
                                            <b>Kotak Styrofoam</b>: Berfungsi sebagai bak penampung Styrofoam
                                            <div class="img"><img src="img/styrofoam.png" alt=""></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Pot Plastik (Netpot)</b>: Wadah untuk menanam bibit
                                            <div class="img"><img src="img/netpot.jpg" alt="" style="height: 200px;"></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Kain Flanel/Sumbu</b>: Berfungsi sebagai sumbu (wick) untuk mengalirkan nutrisi
                                            <div class="img"><img src="img/sumbu.jpg" alt="" style="height: 160px;"> <img src="img/flanel.jpg" alt="" style="height: 160px;"></div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <b>Botol Plastik (Opsional)</b>: Dapat dimanfaatkan sebagai pengganti pot atau wadah
                                            <div class="img"><img src="" alt=""></div>
                                        </li>
                                    </ul>
                                </li>
                            </ol>"""
            ),
        )
    }
}