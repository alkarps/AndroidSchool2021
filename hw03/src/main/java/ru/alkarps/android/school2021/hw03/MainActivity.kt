package ru.alkarps.android.school2021.hw03

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val russianText = findViewById<TextInputEditText>(R.id.russian_input_text)
        val translitText = findViewById<TextInputEditText>(R.id.traslit_input_text)
        findViewById<Button>(R.id.to_translit).setOnClickListener {
            translate(russianText, translitText)
        }
        findViewById<Button>(R.id.to_russian).setOnClickListener {
            translate(translitText, russianText)
        }
    }

    private fun translate(source: TextInputEditText, destination: TextInputEditText) {
        val newText = source.text?.let { TranslateService.translate(it) } ?: ""
        destination.setText(newText, TextView.BufferType.EDITABLE)
    }
}