package ru.alkarps.android.school2021.hw02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity(private val headerText: String) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val header = findViewById<TextView>(R.id.header_title)
        header.text = headerText

        setButtonListener(R.id.button_to_main_activity, MainActivity::class.java)
        setButtonListener(R.id.button_to_single_top_activity, SingleTopActivity::class.java)
        setButtonListener(R.id.button_to_single_task_activity, SingleTaskActivity::class.java)
    }

    private fun setButtonListener(id: Int, clazz: Class<*>) {
        findViewById<Button>(id).setOnClickListener {
            val intent = Intent(this, clazz).apply {
//            putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
    }
}