package ru.alkarps.android.school2021.hw08

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ru.alkarps.android.school2021.hw08.draw.DrawView
import ru.alkarps.android.school2021.hw08.draw.factory.EnumShapeFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        val drawView = findViewById<DrawView>(R.id.draw)
        findViewById<Button>(R.id.btn_reset).setOnClickListener { drawView.reset() }
        findViewById<Button>(R.id.btn_curve).setOnClickListener {
            drawView.changeShape(EnumShapeFactory.CURVE)
        }
        findViewById<Button>(R.id.btn_rect).setOnClickListener {
            drawView.changeShape(EnumShapeFactory.RECT)
        }
        findViewById<Button>(R.id.btn_line).setOnClickListener {
            drawView.changeShape(EnumShapeFactory.LINE)
        }
    }
}