package ru.alkarps.android.school2021.hw08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.alkarps.android.school2021.hw08.draw.DrawView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        val drawView = findViewById<DrawView>(R.id.draw)
        findViewById<Button>(R.id.btn_reset).setOnClickListener { drawView.reset() }
    }
}