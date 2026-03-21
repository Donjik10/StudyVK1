package com.example.intentsapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView = findViewById<TextView>(R.id.textView)

        // Получаем текст из Intent
        val receivedText = intent.getStringExtra("EXTRA_TEXT")

        // Отображаем текст
        if (receivedText != null) {
            textView.text = receivedText
        } else {
            textView.text = "Текст не получен"
        }
    }
}