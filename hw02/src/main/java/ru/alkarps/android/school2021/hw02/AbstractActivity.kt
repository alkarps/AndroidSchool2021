package ru.alkarps.android.school2021.hw02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity(
    private val headerText: String,
    private val needAddCount: Boolean = false
) : AppCompatActivity() {
    protected var count = 0
    private var hasOutIncome = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val header = findViewById<TextView>(R.id.header_title)
        header.text = headerText

        var message = intent.getStringExtra(OUT_APP_MESSAGE_ID)
        if (message != null) hasOutIncome = true
        else message = intent.getStringExtra(IN_APP_MESSAGE_ID) ?: ""

        findViewById<TextView>(R.id.text_message_view).apply {
            text = message
        }
        setButtonListener(R.id.button_to_main_activity, MainActivity::class.java)
        setButtonListener(R.id.button_to_single_top_activity, SingleTopActivity::class.java)
        setButtonListener(R.id.button_to_single_task_activity, SingleTaskActivity::class.java)
    }

    private fun setButtonListener(id: Int, clazz: Class<*>) {
        findViewById<Button>(id).setOnClickListener {
            val intent = Intent(this, clazz).apply {
                putExtra(IN_APP_MESSAGE_ID, "Это сообщение отправлено внутри приложения")
            }
            startActivity(intent)
        }
    }

    companion object Const {
        const val IN_APP_MESSAGE_ID = "IN_APP_MESSAGE"
        const val OUT_APP_MESSAGE_ID = "message"
    }
}