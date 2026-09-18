# 🖥️ Ayo Belajar Jaringan — Troubleshooting Komputer SMK

Aplikasi Android media pembelajaran interaktif untuk siswa **SMK** yang berfokus pada topik **troubleshooting komputer** (diagnosa & perbaikan kerusakan hardware/software), dilengkapi materi, studi kasus, kuis, dan **chatbot AI** sebagai "Guru Troubleshooting" virtual. Aplikasi ini berjalan sepenuhnya secara *offline* (tanpa backend API), kecuali fitur chatbot yang memanggil Gemini API.

## ✨ Fitur Utama

- **Chatbot AI "Guru Troubleshooting"** — tanya jawab interaktif untuk mendiagnosa kerusakan hardware/software, didukung Google Generative AI dengan model **`gemini-3.1-flash-lite`** dan *system instruction* khusus sebagai guru ahli troubleshooting komputer SMK.
- **Materi Pembelajaran** — kumpulan materi troubleshooting (power supply, RAM, motherboard, display/VGA, storage, dsb.) yang ditampilkan sebagai konten detail per topik.
- **Kuis Interaktif** — soal pilihan ganda dengan timer per soal, progress bar, penghitungan skor benar/salah, dan getaran (haptic feedback) sebagai umpan balik.
- **Studi Kasus** — skenario kasus kerusakan komputer untuk melatih kemampuan analisis siswa.
- **CP & TP Pembelajaran** — halaman Capaian Pembelajaran (CP) dan Tujuan Pembelajaran (TP) sesuai Kurikulum Merdeka mata pelajaran Troubleshooting.
- **Profil Pengembang & Informasi Aplikasi** — halaman info seputar aplikasi dan pengembangnya.

## 🛠️ Tech Stack

| Kategori | Teknologi |
|---|---|
| Bahasa | Kotlin |
| AI | Google Generative AI SDK — model `gemini-3.1-flash-lite` |
| UI | Material Components (MaterialCardView, MaterialToolbar), Edge-to-Edge |
| Build Tool | Gradle (Kotlin DSL) dengan Version Catalog (`libs.versions.toml`) |
| Min SDK / Target SDK / Compile SDK | 24 / 37 / 37 |
| Bahasa Java/Kotlin lain | Java 11 (source & target compatibility) |

> Catatan: proyek ini **tidak menggunakan Retrofit/OkHttp atau backend REST API** — seluruh materi, kuis, dan studi kasus bersifat statis/lokal di dalam aplikasi. Hanya fitur chatbot yang melakukan panggilan jaringan, langsung ke Gemini API.

## 🏗️ Struktur Proyek

```
app/src/main/java/com/activity/ayobelajarjaringan/
├── MainActivity.kt          # Menu utama (navigasi ke semua fitur)
├── ChatbotActivity.kt       # Chatbot AI "Guru Troubleshooting" (Gemini)
├── ChatAdapter.kt           # Adapter RecyclerView untuk bubble chat
├── ChatMessage.kt           # Model data pesan chat
├── MateriActivity.kt        # Daftar & detail materi troubleshooting
├── MateriDetailActivity.kt  # Halaman detail satu materi
├── QuizActivity.kt          # Logika kuis (timer, skor, hasil)
├── Question.kt              # Model data soal kuis
├── StudiKasusActivity.kt    # Halaman studi kasus
├── CpTpActivity.kt          # Halaman CP & TP Kurikulum Merdeka
├── ProfilActivity.kt        # Profil pengembang
└── InfoActivity.kt          # Informasi aplikasi
```

## 🚀 Instalasi & Menjalankan Proyek

### Prasyarat
- Android Studio (versi terbaru direkomendasikan)
- JDK 11+
- Perangkat/emulator dengan Android 7.0 (API 24) ke atas

### Langkah

1. **Clone repository**
   ```bash
   git clone https://github.com/mhakbar24/Ayo-Belajar-Jaringan.git
   ```
2. **Buka di Android Studio**
   - Pilih `Open`, arahkan ke folder hasil clone.
3. **Sinkronkan Gradle**
   - Tunggu proses Gradle sync selesai secara otomatis.
4. **Isi API Key Gemini**
   - Buka `ChatbotActivity.kt`, ganti nilai variabel `apiKey` (saat ini masih placeholder) dengan API key Gemini kamu sendiri. Sebaiknya API key **tidak di-hardcode langsung** di kode, melainkan disimpan di `local.properties` / `BuildConfig` agar tidak ikut ter-commit ke repository publik.
5. **Jalankan aplikasi**
   - Pilih emulator atau perangkat fisik, lalu klik `Run ▶`.



## 🤝 Kontribusi

Kontribusi berupa laporan bug, saran fitur, maupun pull request sangat terbuka. Silakan buat *issue* terlebih dahulu untuk mendiskusikan perubahan besar.


## 👤 Pengembang

Dikembangkan oleh [mhakbar24](https://github.com/mhakbar24) sebagai media pembelajaran troubleshooting komputer berbasis AI untuk siswa SMK.

---

# 🖥️ Ayo Belajar Jaringan — Computer Troubleshooting for Vocational Students (SMK)

An interactive Android learning app for **vocational high school (SMK)** students focused on **computer troubleshooting** (diagnosing and fixing hardware/software issues), featuring learning materials, case studies, quizzes, and an **AI chatbot** acting as a virtual "Troubleshooting Teacher". The app runs entirely **offline** (no backend API), except for the chatbot feature which calls the Gemini API.

## ✨ Key Features

- **AI Chatbot "Troubleshooting Teacher"** — interactive Q&A to diagnose hardware/software issues, powered by Google Generative AI with the **`gemini-3.1-flash-lite`** model and a dedicated system instruction acting as an expert computer troubleshooting teacher for SMK students.
- **Learning Materials** — a collection of troubleshooting materials (power supply, RAM, motherboard, display/VGA, storage, etc.) shown as detailed content per topic.
- **Interactive Quiz** — multiple-choice questions with a per-question timer, progress bar, correct/incorrect score tracking, and haptic feedback.
- **Case Studies** — computer damage scenarios to train students' analytical skills.
- **CP & TP (Learning Outcomes & Objectives)** — a page presenting the *Capaian Pembelajaran* (Learning Outcomes) and *Tujuan Pembelajaran* (Learning Objectives) based on Indonesia's Merdeka Curriculum for the Troubleshooting subject.
- **Developer Profile & App Info** — pages with information about the app and its developer.

## 🛠️ Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin |
| AI | Google Generative AI SDK — `gemini-3.1-flash-lite` model |
| UI | Material Components (MaterialCardView, MaterialToolbar), Edge-to-Edge |
| Build Tool | Gradle (Kotlin DSL) with Version Catalog (`libs.versions.toml`) |
| Min SDK / Target SDK / Compile SDK | 24 / 37 / 37 |
| Java/Kotlin compatibility | Java 11 (source & target compatibility) |

> Note: this project **does not use Retrofit/OkHttp or a backend REST API** — all materials, quizzes, and case studies are static/local within the app. Only the chatbot feature performs network calls, directly to the Gemini API.

## 🏗️ Project Structure

```
app/src/main/java/com/activity/ayobelajarjaringan/
├── MainActivity.kt          # Main menu (navigation to all features)
├── ChatbotActivity.kt       # AI Chatbot "Troubleshooting Teacher" (Gemini)
├── ChatAdapter.kt           # RecyclerView adapter for chat bubbles
├── ChatMessage.kt           # Chat message data model
├── MateriActivity.kt        # Troubleshooting material list & detail
├── MateriDetailActivity.kt  # Single material detail screen
├── QuizActivity.kt          # Quiz logic (timer, score, result)
├── Question.kt              # Quiz question data model
├── StudiKasusActivity.kt    # Case study screen
├── CpTpActivity.kt          # Learning outcomes & objectives screen
├── ProfilActivity.kt        # Developer profile
└── InfoActivity.kt          # App information
```

## 🚀 Installation & Running the Project

### Prerequisites
- Android Studio (latest version recommended)
- JDK 11+
- A device/emulator running Android 7.0 (API 24) or higher

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/mhakbar24/Ayo-Belajar-Jaringan.git
   ```
2. **Open in Android Studio**
   - Select `Open`, then point it to the cloned folder.
3. **Sync Gradle**
   - Wait for the Gradle sync process to finish automatically.
4. **Set your Gemini API key**
   - Open `ChatbotActivity.kt` and replace the `apiKey` variable (currently a placeholder) with your own Gemini API key. It's recommended **not to hardcode** the key directly in source — store it in `local.properties` / a `BuildConfig` field instead so it isn't committed to a public repository.
5. **Run the app**
   - Select an emulator or physical device, then click `Run ▶`.

> ⚠️ **API Key Security**: this repository ships with an empty `apiKey` placeholder in `ChatbotActivity.kt`. Never commit a real API key to a public repository — use a git-ignored `local.properties` file or a `BuildConfig` field instead.

## 🤝 Contributing

Bug reports, feature suggestions, and pull requests are welcome. Please open an issue first to discuss any major changes.


## 👤 Developer

Developed by [mhakbar24](https://github.com/mhakbar24) as an AI-powered computer troubleshooting learning app for vocational high school (SMK) students.
