package com.activity.ayobelajarjaringan

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class QuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var score = 0
    private var correctCount = 0
    private var wrongCount = 0
    private var timer: CountDownTimer? = null
    
    private lateinit var tvQueCount: TextView
    private lateinit var tvTimer: TextView
    private lateinit var timerProgress: ProgressBar
    private lateinit var tvScore: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var tvQuestionNum: TextView
    
    private lateinit var options: List<MaterialCardView>
    private lateinit var optionTexts: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        loadQuestions()
        showQuestion()

        findViewById<android.widget.ImageButton>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initViews() {
        tvQueCount = findViewById(R.id.tvQueCount)
        tvTimer = findViewById(R.id.tvTimer)
        timerProgress = findViewById(R.id.timerProgress)
        tvScore = findViewById(R.id.tvScore)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvQuestionNum = findViewById(R.id.tvQuestionNum)

        options = listOf(
            findViewById(R.id.option1),
            findViewById(R.id.option2),
            findViewById(R.id.option3),
            findViewById(R.id.option4)
        )

        optionTexts = listOf(
            findViewById(R.id.tvOption1),
            findViewById(R.id.tvOption2),
            findViewById(R.id.tvOption3),
            findViewById(R.id.tvOption4)
        )

        options.forEachIndexed { index, card ->
            card.setOnClickListener { checkAnswer(index + 1) }
        }
    }

    private fun loadQuestions() {
        questions = listOf(
            Question(1, "Komputer tidak menyala sama sekali dan kipas PSU tidak berputar, kemungkinan kerusakan pada...", "RAM", "Processor", "Power Supply", "Monitor", 3),
            Question(2, "Bunyi Beep 1x panjang dan 3x pendek pada BIOS AMI menandakan masalah pada...", "VGA Card", "RAM", "Keyboard", "Power Supply", 1),
            Question(3, "Penyebab utama munculnya Blue Screen of Death (BSOD) adalah...", "Keyboard rusak", "RAM atau Harddisk bermasalah", "Mouse tidak terdeteksi", "Speaker mati", 2),
            Question(4, "Komputer sering restart sendiri secara tiba-tiba biasanya disebabkan oleh...", "Overheat pada Processor", "Keyboard kotor", "Kabel LAN putus", "Speaker rusak", 1),
            Question(5, "Pesan 'Operating System Not Found' muncul saat booting karena...", "RAM kotor", "VGA rusak", "Urutan boot salah atau HDD rusak", "Printer mati", 3),
            Question(6, "Jika tanggal di Windows selalu kembali ke tahun lama setiap dimatikan, komponen yang harus diganti adalah...", "Baterai CMOS", "Harddisk", "Power Supply", "Monitor", 1),
            Question(7, "Komputer menyala tapi layar tetap hitam (No Display), langkah pertama yang harus dilakukan adalah...", "Ganti Processor", "Bersihkan pin RAM dengan penghapus", "Instal ulang Windows", "Ganti Casing", 2),
            Question(8, "Harddisk tidak terdeteksi di BIOS, kemungkinan kabel yang bermasalah adalah...", "Kabel SATA", "Kabel Audio", "Kabel LAN", "Kabel VGA", 1),
            Question(9, "Kipas processor berputar sangat cepat dan berisik, tindakan pencegahannya adalah...", "Ganti Mouse", "Bersihkan debu dan ganti Thermal Paste", "Ganti Monitor", "Matikan Speaker", 2),
            Question(10, "Alat yang digunakan untuk mengecek tegangan pada Power Supply adalah...", "Obeng", "Tang Kombinasi", "Multimeter", "Penghapus", 3)
        ).shuffled()
    }

    private fun showQuestion() {
        if (currentQuestionIndex < questions.size) {
            val q = questions[currentQuestionIndex]
            
            resetOptions()
            
            tvQueCount.text = "Que ${currentQuestionIndex + 1}/${questions.size}"
            tvQuestionNum.text = "Question ${currentQuestionIndex + 1}"
            tvQuestion.text = q.question
            optionTexts[0].text = q.option1
            optionTexts[1].text = q.option2
            optionTexts[2].text = q.option3
            optionTexts[3].text = q.option4
            
            startTimer()
        } else {
            showResultDialog()
        }
    }

    private fun showResultDialog() {
        val dialog = android.app.Dialog(this)
        dialog.setContentView(R.layout.dialog_quiz_result)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)

        val tvFinalScore = dialog.findViewById<TextView>(R.id.tvFinalScore)
        val tvCorrectCount = dialog.findViewById<TextView>(R.id.tvCorrectCount)
        val tvWrongCount = dialog.findViewById<TextView>(R.id.tvWrongCount)
        val btnFinish = dialog.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnFinish)

        tvFinalScore.text = score.toString()
        tvCorrectCount.text = correctCount.toString()
        tvWrongCount.text = wrongCount.toString()

        btnFinish.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        dialog.show()
    }

    private fun startTimer() {
        timer?.cancel()
        timerProgress.progress = 100
        
        timer = object : CountDownTimer(15000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                tvTimer.text = String.format("%02d", seconds)
                timerProgress.progress = (millisUntilFinished / 150.0).toInt()
            }

            override fun onFinish() {
                nextQuestion()
            }
        }.start()
    }

    private fun checkAnswer(selectedOption: Int) {
        timer?.cancel()
        val q = questions[currentQuestionIndex]
        
        // Disable clicks
        options.forEach { it.isClickable = false }
        
        if (selectedOption == q.answer) {
            score += 10
            correctCount++
            tvScore.text = score.toString()
            options[selectedOption - 1].setCardBackgroundColor(Color.parseColor("#C8E6C9")) // Light Green
            options[selectedOption - 1].strokeColor = Color.parseColor("#4CAF50")
        } else {
            wrongCount++
            vibrateError()
            options[selectedOption - 1].setCardBackgroundColor(Color.parseColor("#FFCDD2")) // Light Red
            options[selectedOption - 1].strokeColor = Color.parseColor("#F44336")
            
            // Show correct answer
            options[q.answer - 1].setCardBackgroundColor(Color.parseColor("#C8E6C9"))
            options[q.answer - 1].strokeColor = Color.parseColor("#4CAF50")
        }
        
        options[selectedOption - 1].postDelayed({
            nextQuestion()
        }, 1500)
    }

    private fun nextQuestion() {
        currentQuestionIndex++
        showQuestion()
    }

    private fun resetOptions() {
        options.forEach { card ->
            card.setCardBackgroundColor(Color.WHITE)
            card.strokeColor = Color.parseColor("#EEEEEE")
            card.isClickable = true
        }
    }

    private fun vibrateError() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(200)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}