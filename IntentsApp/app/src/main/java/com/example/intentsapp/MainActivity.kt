package com.example.intentsapp
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var btnOpenSecond: Button
    private lateinit var btnCall: Button
    private lateinit var btnShare: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        btnOpenSecond = findViewById(R.id.btnOpenSecond)
        btnCall = findViewById(R.id.btnCall)
        btnShare = findViewById(R.id.btnShare)
        //Явный Intent
        btnOpenSecond.setOnClickListener {
            val text = editText.text.toString()
            if (text.isBlank()) {
                Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //явный Intent
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("EXTRA_TEXT", text)
            startActivity(intent)
        }

        //Неявный Intent - звонок
        btnCall.setOnClickListener {
            val phoneNumber = editText.text.toString()
            if (phoneNumber.isBlank()) {
                Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //неявный Intent для набора номера
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        }
        //Системный Intent - поделиться
        btnShare.setOnClickListener {
            val text = editText.text.toString()
            if (text.isBlank()) {
                Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //системный Intent для отправки текста
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
            //системное окно выбора приложения
            startActivity(Intent.createChooser(intent, "Поделиться через"))
        }
    }
}