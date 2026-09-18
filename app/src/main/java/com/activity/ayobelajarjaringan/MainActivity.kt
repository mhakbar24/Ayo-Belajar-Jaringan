package com.activity.ayobelajarjaringan

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Toast
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupMenu()

        findViewById<MaterialCardView>(R.id.btnInfo).setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnWatchNow).setOnClickListener {
            startActivity(Intent(this, CpTpActivity::class.java))
        }
    }

    private fun setupMenu() {
        findViewById<MaterialCardView>(R.id.btnStudiKasus).setOnClickListener {
            startActivity(Intent(this, StudiKasusActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.btnQuiz).setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.btnMateri).setOnClickListener {
            startActivity(Intent(this, MateriActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.btnChatbot).setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.btnProfil).setOnClickListener {
            startActivity(Intent(this, ProfilActivity::class.java))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}