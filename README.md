# Lettuce SmartHidro (Deloteng SmartHidro)

## Deskripsi Proyek
Lettuce SmartHidro adalah aplikasi Android terintegrasi yang menyediakan panduan budidaya hidroponik interaktif sekaligus sistem deteksi dan klasifikasi penyakit pada daun selada. Aplikasi ini dirancang untuk mempermudah pemantauan kesehatan tanaman secara praktis menggunakan teknologi *Machine Learning* yang dijalankan langsung di perangkat seluler (on-device).

## Fitur Utama
*   **Deteksi Penyakit Daun Selada:** Mengklasifikasikan kondisi daun selada berdasarkan gambar menggunakan model *Deep Learning*.
*   **Pemrosesan Gambar Fleksibel:** Pengguna dapat mengambil foto atau memilih gambar dari galeri, lalu memotong (*crop*) bagian daun yang ingin dianalisis untuk hasil yang lebih akurat.
*   **Panduan Interaktif:** Menyediakan pemutaran video panduan seputar budidaya tanaman hidroponik secara mulus.
*   **Penyimpanan Data Lokal:** Menyimpan riwayat deteksi dan preferensi pengguna untuk akses cepat tanpa internet.
*   **Peta & Lokasi:** Mendukung pelacakan lokasi atau titik budidaya hidroponik (menggunakan Google Maps).

## Arsitektur Machine Learning
Model pendeteksi penyakit dibangun menggunakan arsitektur **Convolutional Neural Network (CNN)** dengan basis pre-trained model **MobileNetV2**.
*   **Framework Training:** TensorFlow.
*   **Format Deployment:** TensorFlow Lite (`.tflite`) untuk inferensi yang ringan dan cepat di perangkat Android.
*   **Library Integrasi:** TensorFlow Lite Support, Metadata, dan Task Vision.

## Tech Stack & Dependencies

Aplikasi ini dikembangkan menggunakan **Kotlin (1.9.22)** dan mengimplementasikan berbagai *library* modern Android:

### 1. Core & UI
*   **UI Framework:** Material Design (`1.12.0`), AppCompat (`1.7.1`), ConstraintLayout (`2.2.1`).
*   **Image Loading & Processing:** Glide (`4.16.0`), uCrop (`2.2.8`).
*   **Media Player:** AndroidX Media3 ExoPlayer (`1.8.0`) untuk memutar video panduan.
*   **Loading Effect:** Facebook Shimmer (`0.5.0`).

### 2. Architecture Components
*   **Navigation:** AndroidX Navigation Component (`2.9.3`) untuk perpindahan antar Fragment.
*   **Lifecycle:** ViewModel & LiveData (`2.9.4`) untuk manajemen *state* yang responsif.
*   **Data Storage:** 
    *   Room Database (`2.6.1`) untuk penyimpanan relasional lokal.
    *   DataStore Preferences (`1.2.0`) untuk penyimpanan *key-value* asinkron.

### 3. Network & Location
*   **API Client:** Retrofit (`2.11.0`) dengan Gson Converter untuk mengambil data dari server eksternal.
*   **Network Logger:** OkHttp Logging Interceptor (`4.12.0`).
*   **Location Services:** Google Play Services Location (`21.3.0`) & Maps (`19.0.0`).

## Persyaratan Sistem
*   Android Studio (versi terbaru disarankan yang mendukung Android Gradle Plugin `8.6.1`).
*   Minimum SDK: (Sesuaikan dengan `minSdk` di `build.gradle`, misal: API 24).
*   Koneksi internet (untuk mengunduh *dependencies* saat *build* pertama kali dan akses fitur Maps/API).

## Cara Menjalankan Proyek (Instalasi)
1. **Clone Repository** ini ke mesin lokal Anda:
   ```bash
   git clone <url-repository-anda>
   ```
2. **Buka Proyek** menggunakan Android Studio.
3. Tunggu hingga proses **Gradle Sync** selesai. Pastikan koneksi internet stabil agar semua *dependencies* (termasuk Glide, Retrofit, Room, Media3, dll.) berhasil diunduh.
4. (Opsional) Jika menggunakan API eksternal (seperti Google Maps), pastikan Anda telah memasukkan `API_KEY` Anda di `local.properties` atau `AndroidManifest.xml`.
5. Sambungkan perangkat Android fisik atau jalankan emulator.
6. Klik tombol **Run** (Shift + F10) di Android Studio.

## Pengujian (Testing)
Proyek ini dikonfigurasi dengan *library* pengujian standar Android:
*   **Unit Testing:** JUnit 4 (`4.13.2`)
*   **Instrumentation Testing:** AndroidX JUnit Ext (`1.3.0`) & Espresso Core (`3.7.0`)
