package ru.alkarps.android.school2021.hw02

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity(
    private val headerText: String
) : AppCompatActivity() {
    private lateinit var inCallTextView: TextView
    private lateinit var outCallTextView: TextView
    private var outCallText: String? = null
    private var inCallCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val header = findViewById<TextView>(R.id.header_title)
        header.text = headerText

        inCallTextView = findViewById(R.id.text_message_view_in_call_count)
        outCallTextView = findViewById(R.id.text_message_view_out_call_count)

        updateTextViewIfNeed()

        setButtonListener(R.id.button_to_main_activity, MainActivity::class.java)
        setButtonListener(R.id.button_to_single_top_activity, SingleTopActivity::class.java)
        setButtonListener(R.id.button_to_single_task_activity, SingleTaskActivity::class.java)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        updateTextViewIfNeed()
    }

    private fun updateTextViewIfNeed() {
        intent.getStringExtra(IN_APP_MESSAGE_ID)?.apply {
            inCallCount++
            inCallTextView.apply {
                text = "Количество вызово внутри приложения: $inCallCount"
            }
        }
        if (intent.action == ACTION_VIEW) intent.data?.getQueryParameter(OUT_APP_MESSAGE_ID).apply {
            var message =
                if (outCallText != null) "Предыдущее полученное сообщение: \"$outCallText\""
                else ""
            outCallText = this ?: "null"
            message = "Сообщение полученное извне приложения: \"$outCallText\". $message"
            outCallTextView.apply {
                text = message
            }
        }
    }

    private fun setButtonListener(id: Int, clazz: Class<*>) {
        findViewById<Button>(id).setOnClickListener {
            val intent = Intent(this, clazz).apply {
                putExtra(IN_APP_MESSAGE_ID, IN_APP_MESSAGE_ID)
            }
            startActivity(intent)
        }
    }

    companion object Const {
        const val IN_APP_MESSAGE_ID = "IN_APP_MESSAGE"
        const val OUT_APP_MESSAGE_ID = "message"
    }
}