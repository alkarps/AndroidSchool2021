
package ru.alkarps.android.school2021.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var winCounter = 0
    private var defeatCounter = 0
    private lateinit var winCounterText: TextView
    private lateinit var winCounterImage: ImageView
    private lateinit var defeatCounterText: TextView
    private lateinit var defeatCounterImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        winCounterText = findViewById(R.id.win_counter_text)
        winCounterText.text = winCounter.toString()
        winCounterImage = findViewById(R.id.win_counter_image)
        defeatCounterText = findViewById(R.id.defeat_counter_text)
        defeatCounterText.text = defeatCounter.toString()
        defeatCounterImage = findViewById(R.id.defeat_counter_image)
    }
}