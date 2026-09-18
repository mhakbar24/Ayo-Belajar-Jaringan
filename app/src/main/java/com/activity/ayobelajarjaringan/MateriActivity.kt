package com.activity.ayobelajarjaringan

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.card.MaterialCardView

class MateriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_materi)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        findViewById<MaterialCardView>(R.id.btnMateri1).setOnClickListener {
            openDetail(getString(R.string.title_materi_1), getMateriContent(1), R.drawable.powersuplay)
        }
        findViewById<MaterialCardView>(R.id.btnMateri2).setOnClickListener {
            openDetail(getString(R.string.title_materi_2), getMateriContent(2), R.drawable.motherboard)
        }
        findViewById<MaterialCardView>(R.id.btnMateri3).setOnClickListener {
            openDetail(getString(R.string.title_materi_3), getMateriContent(3), R.drawable.ramm)
        }
        findViewById<MaterialCardView>(R.id.btnMateri4).setOnClickListener {
            openDetail(getString(R.string.title_materi_4), getMateriContent(4), R.drawable.storage)
        }
        findViewById<MaterialCardView>(R.id.btnMateri5).setOnClickListener {
            openDetail(getString(R.string.title_materi_5), getMateriContent(5), R.drawable.dislay)
        }
        findViewById<MaterialCardView>(R.id.btnMateri6).setOnClickListener {
            openDetail(getString(R.string.title_materi_6), getMateriContent(6), R.drawable.maintaenance)
        }
    }

    private fun openDetail(title: String, content: String, imageRes: Int) {
        val intent = Intent(this, MateriDetailActivity::class.java).apply {
            putExtra("EXTRA_TITLE", title)
            putExtra("EXTRA_CONTENT", content)
            putExtra("EXTRA_IMAGE", imageRes)
        }
        startActivity(intent)
    }

    private fun getMateriContent(id: Int): String {
        return when (id) {
            1 -> """
                MODUL LENGKAP: TROUBLESHOOTING POWER SUPPLY UNIT (PSU)
                Standardisasi Alur Tujuan Pembelajaran (ATP) Fase F - SMK TKJ
                
                A. PENDAHULUAN & K3LH
                PSU adalah komponen vital yang mengonversi arus AC (220V) menjadi DC (+3.3V, +5V, +12V). Sebelum melakukan troubleshooting, pastikan menggunakan gelang antistatis dan alas kaki karet untuk menghindari sengatan listrik.
                
                B. DIAGNOSIS LEVEL 1: GEJALA FISIK & INDIKASI
                1. PC Mati Total: Tidak ada perputaran kipas (fan), lampu indikator motherboard mati.
                2. Sering Restart/Hang: Daya tidak stabil (ripples) saat beban puncak (gaming/rendering).
                3. Bau Terbakar: Kerusakan pada kapasitor atau MOSFET internal PSU.
                4. Casing Menyengat: Grounding kabel power atau instalasi listrik gedung bermasalah.
                
                C. DIAGNOSIS LEVEL 2: PENGUJIAN TEKNIS
                1. Paper Clip Test (Jumpering):
                   - Lepas kabel 24-pin dari motherboard.
                   - Hubungkan kabel HIJAU (PS_ON) dengan salah satu kabel HITAM (Ground/COM).
                   - Jika kipas berputar, sirkuit trigger PSU bekerja. Jika tidak, PSU dipastikan mati total.
                2. Pengukuran Voltase (Multimeter Digital):
                   - Skala DC 20V.
                   - Kabel Kuning: Harus +12V (Toleransi ±5%). Jika di bawah 11.4V, sistem akan tidak stabil.
                   - Kabel Merah: Harus +5V.
                   - Kabel Oranye: Harus +3.3V.
                   - Kabel Ungu (5V Standby): Harus selalu ada tegangan meski PC dalam posisi OFF (selama kabel power tercolok).
                
                D. SOLUSI & PEMELIHARAAN
                1. Pembersihan: Gunakan kuas dan blower untuk membersihkan debu pada kipas yang menghambat airflow.
                2. Penggantian: Jika ditemukan kapasitor kembung, disarankan mengganti unit PSU dengan yang memiliki sertifikasi minimal 80 Plus untuk efisiensi daya.
                3. Proteksi: Gunakan UPS atau Stavolt untuk melindungi PSU dari lonjakan tegangan (voltage spike).
            """.trimIndent()
            
            2 -> """
                MODUL LENGKAP: TROUBLESHOOTING MOTHERBOARD & CPU
                Standardisasi Alur Tujuan Pembelajaran (ATP) Fase F - SMK TKJ
                
                A. DIAGNOSIS POST (POWER ON SELF TEST)
                POST adalah prosedur diagnosa otomatis oleh BIOS saat komputer dinyalakan.
                1. Analisis Beep Code (BIOS AMI):
                   - 1 Beep Pendek: Sistem Normal.
                   - 3 Beep Pendek: Kerusakan pada 64K dasar memori.
                   - 5 Beep Pendek: Kerusakan pada Processor.
                   - 8 Beep Pendek: Kerusakan pada Video Adapter (VGA).
                2. Kode Angka Debug (Motherboard High-End): Cek manual book untuk kode HEX (misal: 00, FF, C1).
                
                B. PERMASALAHAN CPU (PROCESSOR)
                1. Overheating:
                   - Gejala: PC tiba-tiba mati setelah 5-10 menit menyala.
                   - Penyebab: Thermal paste kering, fan heatsink mati, atau heatsink kendor.
                   - Solusi: Re-pasting menggunakan thermal paste berbahan carbon/silver, bersihkan debu sirip heatsink.
                2. Thermal Throttling: Penurunan clock speed otomatis karena suhu terlalu panas, menyebabkan PC terasa sangat lambat (lagging).
                
                C. PERMASALAHAN MOTHERBOARD
                1. CMOS Error:
                   - Gejala: Tanggal/waktu sistem selalu reset ke tahun lama, muncul pesan "CMOS Checksum Error".
                   - Solusi: Ganti baterai CR2032.
                2. BIOS Corrupt: Kegagalan sistem booting setelah proses update BIOS yang gagal atau serangan virus tertentu.
                   - Solusi: Clear CMOS menggunakan Jumper (JBAT1) atau flash ulang BIOS.
                3. Kerusakan Fisik: Jalur PCB terbakar atau Kapasitor Kembung (Caps Leak) akibat lonjakan arus.
                
                D. LANGKAH DIAGNOSA LANJUTAN
                Gunakan "Motherboard Diagnostic Card" (POST Card) untuk melihat titik kegagalan pada jalur data atau alamat.
            """.trimIndent()
            
            3 -> """
                MODUL LENGKAP: TROUBLESHOOTING RAM (RANDOM ACCESS MEMORY)
                Standardisasi Alur Tujuan Pembelajaran (ATP) Fase F - SMK TKJ
                
                A. GEJALA KHAS KERUSAKAN RAM
                1. PC Menyala Tapi Tidak Tampil (No Display): Kipas CPU berputar tapi layar monitor hitam.
                2. Bunyi Beep Berulang: Umumnya beep panjang berulang-ulang.
                3. Blue Screen of Death (BSOD): Muncul pesan error seperti "MEMORY_MANAGEMENT" atau "PAGE_FAULT_IN_NONPAGED_AREA".
                4. Restart Acak: PC tiba-tiba restart saat membuka aplikasi berat.
                
                B. TEKNIK DIAGNOSIS & REPARASI
                1. Metode Pembersihan (Gold Finger Cleaning):
                   - Lepas modul RAM.
                   - Bersihkan pin emas menggunakan penghapus pensil putih (jangan gunakan cairan kecuali Contact Cleaner).
                   - Semprot slot RAM dengan udara bertekanan untuk membuang debu.
                2. Metode Reseat & Swap:
                   - Pasang kembali RAM pada slot yang berbeda.
                   - Jika menggunakan dual channel, coba pasang satu keping secara bergantian untuk mengidentifikasi modul yang rusak.
                3. Software Testing (MemTest86):
                   - Lakukan booting melalui USB Flashdisk yang berisi MemTest86.
                   - Jika muncul baris MERAH, berarti ada sel memori yang rusak permanen.
                
                C. KOMPATIBILITAS
                Pastikan frekuensi (MHz) dan voltase (DDR3L 1.35V vs DDR3 1.5V) sesuai dengan spesifikasi motherboard. Ketidakcocokan ini sering menyebabkan sistem tidak stabil.
            """.trimIndent()

            4 -> """
                MODUL LENGKAP: TROUBLESHOOTING STORAGE DEVICE (HDD/SSD)
                Standardisasi Alur Tujuan Pembelajaran (ATP) Fase F - SMK TKJ
                
                A. DIAGNOSIS MEDIA PENYIMPANAN
                1. Gejala Fisik HDD: Bunyi "klik-klik" (Click of Death) menandakan head pembaca membentur piringan. Segera backup data!
                2. Pesan Error Booting:
                   - "Disk Boot Failure, Insert System Disk".
                   - "Operating System Not Found".
                
                B. ANALISIS KESEHATAN (S.M.A.R.T)
                Self-Monitoring, Analysis, and Reporting Technology. Gunakan software seperti CrystalDiskInfo.
                - Status "Caution": Terdapat sektor yang direlokasi (Reallocated Sector Count). Segera ganti drive.
                - Status "Bad": Drive hampir mati, resiko kehilangan data 100%.
                
                C. MASALAH PADA SSD (SOLID STATE DRIVE)
                1. Read-Only Mode: SSD tidak bisa menyimpan file baru tapi bisa dibaca. Ini adalah mode proteksi saat sel flash mencapai limit umur.
                2. Tidak Terdeteksi: Umumnya akibat kerusakan controller atau konektor kabel data/daya.
                
                D. TROUBLESHOOTING LOGIS (SOFTWARE)
                1. Bad Sector (Logic): Perbaiki menggunakan perintah `chkdsk /f /r` pada Command Prompt.
                2. MBR/GPT Corrupt: Gunakan perintah `bootrec /fixmbr` atau `bootrec /fixboot` melalui Windows Recovery Environment.
                3. Kabel Data: Coba ganti kabel SATA dengan yang baru, karena kabel SATA sering mengalami degradasi sinyal.
            """.trimIndent()

            5 -> """
                MODUL LENGKAP: TROUBLESHOOTING DISPLAY & GPU
                Standardisasi Alur Tujuan Pembelajaran (ATP) Fase F - SMK TKJ
                
                A. ANALISIS GEJALA VISUAL
                1. No Signal: Monitor menyala namun muncul pesan "No Signal".
                2. Artifacting: Muncul garis-garis, kotak-kotak warna-warni, atau glitch pada layar. Ini indikasi kuat kerusakan VRAM pada GPU.
                3. Blank Screen: Layar hitam total setelah logo Windows (seringkali masalah driver).
                
                B. LANGKAH DIAGNOSA BERTAHAP
                1. Cek Kabel Data: Pastikan kabel VGA/HDMI/DisplayPort tidak kendor atau putus jalur di dalam.
                2. Cek Output Source: Jika menggunakan GPU Card, pastikan kabel monitor dicolok ke GPU, BUKAN ke port motherboard.
                3. Safe Mode: Masuk ke Safe Mode untuk menghapus driver lama yang bermasalah (Display Driver Uninstaller/DDU).
                
                C. TROUBLESHOOTING HARDWARE GPU
                1. Overheating GPU: Periksa putaran kipas VGA. Jika suhu di atas 90°C saat idle, ganti thermal paste GPU.
                2. PCIe Power: Pastikan kabel power tambahan (6-pin/8-pin) dari PSU terpasang dengan benar pada kartu grafis.
                3. Slot PCIe: Coba bersihkan pin emas kartu grafis dengan penghapus dan pasang kembali (re-seat).
                
                D. PENGUJIAN STABILITAS
                Gunakan software Stress Test (FurMark atau 3DMark). Jika sistem crash dalam waktu singkat, berarti GPU tidak mampu bekerja pada beban maksimal.
            """.trimIndent()

            6 -> """
                MODUL LENGKAP: MAINTENANCE & PREVENTIVE PERAWATAN
                Standardisasi Alur Tujuan Pembelajaran (ATP) Fase F - SMK TKJ
                
                A. KONSEP PREVENTIVE MAINTENANCE
                Mencegah lebih baik daripada memperbaiki. Perawatan rutin dapat memperpanjang umur hardware hingga 2-3 kali lipat.
                
                B. PERAWATAN PERANGKAT KERAS (HARDWARE)
                1. Dusting: Membersihkan debu setiap 3-6 bulan sekali menggunakan blower atau compressed air. Fokus pada Heatsink, PSU, dan Kipas Casing.
                2. Manajemen Kabel: Pastikan kabel di dalam casing rapi (Cable Management) agar tidak menghambat aliran udara (Airflow).
                3. Pengecekan Koneksi: Memastikan semua komponen terpasang rapat dan tidak ada baut yang kendor.
                
                C. PERAWATAN PERANGKAT LUNAK (SOFTWARE)
                1. Disk Management: Jalankan "Disk Cleanup" untuk menghapus file temp dan "Optimize/Defrag" untuk HDD (Jangan lakukan defrag pada SSD).
                2. Update & Keamanan: Pastikan Windows Update dan Antivirus selalu versi terbaru.
                3. Startup Management: Matikan aplikasi startup yang tidak perlu untuk mempercepat waktu booting.
                
                D. MANAJEMEN DAYA & LINGKUNGAN
                1. Suhu Ruangan: Usahakan suhu ruangan tetap sejuk (ideal 20-25°C) untuk menjaga stabilitas komponen.
                2. UPS (Uninterruptible Power Supply): Wajib digunakan untuk menghindari kerusakan file sistem dan hardware akibat mati lampu mendadak.
                
                E. DOKUMENTASI (LOG BOOK)
                Selalu catat setiap aktivitas perawatan dan perbaikan pada Log Book teknisi sebagai referensi jika terjadi masalah di kemudian hari.
            """.trimIndent()
            
            else -> "Konten belum tersedia secara lengkap sesuai ATP SMK TKJ."
        }
    }
}