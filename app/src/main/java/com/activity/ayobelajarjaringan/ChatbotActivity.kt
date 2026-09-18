package com.activity.ayobelajarjaringan

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch

class ChatbotActivity : AppCompatActivity() {

    private lateinit var chatAdapter: ChatAdapter
    private val chatMessages = mutableListOf<ChatMessage>()
    
    // PERHATIAN: Masukkan API Key Gemini Anda di sini
    private val apiKey = "Masuukan Api Kamu disni"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chatbot)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chatbotMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                if (ime.bottom > 0) ime.bottom else systemBars.bottom
            )
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val rvChat = findViewById<RecyclerView>(R.id.rvChat)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSend = findViewById<ImageButton>(R.id.btnSend)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        chatAdapter = ChatAdapter(chatMessages)
        rvChat.layoutManager = LinearLayoutManager(this)
        rvChat.adapter = chatAdapter

        // Inisialisasi Model Gemini
        val generativeModel = GenerativeModel(
            modelName = "gemini-3.1-flash-lite",
            apiKey = apiKey,
            systemInstruction = content { text("Anda adalah seorang guru ahli dalam bidang Troubleshooting Komputer untuk siswa SMK. Tugas Anda adalah membantu siswa mendiagnosa kerusakan hardware/software, memberikan langkah-langkah perbaikan (troubleshooting), dan memberikan tips perawatan komputer dengan bahasa yang edukatif, ramah, dan mudah dipahami.") }
        )

        btnSend.setOnClickListener {
            val userMessage = etMessage.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                addMessage(userMessage, true)
                etMessage.text.clear()
                
                sendMessageToGemini(generativeModel, userMessage, progressBar)
            }
        }
        
        // Pesan sambutan
        addMessage("Halo! Saya guru ahli troubleshooting komputer kamu. Ada masalah dengan komputermu? Ceritakan gejalanya, saya akan bantu cari solusinya.", false)
    }

    private fun sendMessageToGemini(model: GenerativeModel, userMessage: String, progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        
        lifecycleScope.launch {
            try {
                val response = model.generateContent(userMessage)
                val botResponse = response.text ?: "Maaf, saya tidak mengerti. Bisa diulang?"
                addMessage(botResponse, false)
            } catch (e: Exception) {
                addMessage("Kesalahan: ${e.localizedMessage}. Pastikan API Key valid dan koneksi internet aktif.", false)
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun addMessage(message: String, isUser: Boolean) {
        chatMessages.add(ChatMessage(message, isUser))
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        findViewById<RecyclerView>(R.id.rvChat).scrollToPosition(chatMessages.size - 1)
    }
}