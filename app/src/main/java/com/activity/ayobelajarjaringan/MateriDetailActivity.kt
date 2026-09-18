package com.activity.ayobelajarjaringan

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.LeadingMarginSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class MateriDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_materi_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvContent = findViewById<TextView>(R.id.tvDetailContent)
        val ivMateri = findViewById<ImageView>(R.id.ivMateri)

        val title = intent.getStringExtra("EXTRA_TITLE") ?: "Detail Materi"
        val content = intent.getStringExtra("EXTRA_CONTENT") ?: ""
        val imageRes = intent.getIntExtra("EXTRA_IMAGE", R.drawable.ic_launcher_foreground)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        tvTitle.text = title
        ivMateri.setImageResource(imageRes)
        
        // Advanced Formatting using Spannable for perfect numbering
        tvContent.text = formatProfessionalContent(content)
    }

    private fun formatProfessionalContent(content: String): CharSequence {
        val builder = SpannableStringBuilder()
        val lines = content.lines()
        
        // Indentation dimensions (in pixels - rough estimation, better to use resources in real app)
        val sectionMargin = 0
        val listMargin = 48
        val bulletMargin = 96

        for (line in lines) {
            val trimmedLine = line.trim()
            if (trimmedLine.isEmpty()) {
                builder.append("\n")
                continue
            }

            // Check for Section Headers (A., B., C., etc.)
            if (trimmedLine.matches(Regex("^[A-Z]\\..*"))) {
                builder.append("\n") // Extra space before header
                val headerStart = builder.length
                builder.append(trimmedLine.uppercase())
                builder.setSpan(StyleSpan(Typeface.BOLD), headerStart, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                builder.setSpan(RelativeSizeSpan(1.15f), headerStart, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                builder.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.header_blue)), headerStart, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                builder.append("\n")
            } 
            // Check for Main Points (1., 2., 3., etc.)
            else if (trimmedLine.matches(Regex("^\\d+\\..*"))) {
                val start = builder.length
                builder.append(trimmedLine)
                
                // Bold the number and the period
                val dotIndex = trimmedLine.indexOf(".")
                builder.setSpan(StyleSpan(Typeface.BOLD), start, start + dotIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                
                // Apply LeadingMarginSpan for "Hanging Indent"
                // First line is at listMargin, subsequent lines are indented same amount
                builder.setSpan(LeadingMarginSpan.Standard(listMargin), start, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                builder.append("\n")
            }
            // Check for Bullet Points (-, *, •)
            else if (trimmedLine.startsWith("-") || trimmedLine.startsWith("*") || trimmedLine.startsWith("•")) {
                val start = builder.length
                val bulletText = "• " + trimmedLine.substring(1).trim()
                builder.append(bulletText)
                
                // Indent bullet points further
                builder.setSpan(LeadingMarginSpan.Standard(bulletMargin), start, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                builder.append("\n")
            }
            // Normal Text
            else {
                val start = builder.length
                // If it looks like a subtitle or title
                if (trimmedLine == trimmedLine.uppercase() && trimmedLine.length > 5) {
                    builder.append(trimmedLine)
                    builder.setSpan(StyleSpan(Typeface.BOLD), start, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                } else {
                    builder.append(trimmedLine)
                }
                
                // Add minor indent to normal text so it aligns with headers but stays left of lists
                builder.setSpan(LeadingMarginSpan.Standard(sectionMargin), start, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                builder.append("\n")
            }
        }
        
        return builder
    }
}