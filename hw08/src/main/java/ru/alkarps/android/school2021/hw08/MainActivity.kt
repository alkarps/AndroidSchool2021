package ru.alkarps.android.school2021.hw08

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
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
        initControlButton(drawView)
        initColorControl(drawView)
    }

    private fun initControlButton(drawView: DrawView) {
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
        findViewById<Button>(R.id.btn_pen).setOnClickListener {
            drawView.changeShape(EnumShapeFactory.PEN)
        }
    }

    private fun initColorControl(drawView: DrawView) {
        findViewById<RadioButton>(R.id.color_picker_black).setBackgroundColor(Color.BLACK)
        findViewById<RadioButton>(R.id.color_picker_blue).setBackgroundColor(Color.BLUE)
        findViewById<RadioButton>(R.id.color_picker_cyan).setBackgroundColor(Color.CYAN)
        findViewById<RadioButton>(R.id.color_picker_green).setBackgroundColor(Color.GREEN)
        findViewById<RadioButton>(R.id.color_picker_yellow).setBackgroundColor(Color.YELLOW)
        findViewById<RadioButton>(R.id.color_picker_red).setBackgroundColor(Color.RED)
        findViewById<RadioGroup>(R.id.color_picker).setOnCheckedChangeListener { _, id ->
            drawView.changePaintColor(
                when (id) {
                    R.id.color_picker_black -> Color.BLACK
                    R.id.color_picker_blue -> Color.BLUE
                    R.id.color_picker_cyan -> Color.CYAN
                    R.id.color_picker_green -> Color.GREEN
                    R.id.color_picker_yellow -> Color.YELLOW
                    R.id.color_picker_red -> Color.RED
                    else -> Color.WHITE
                }
            )
        }
    }
}