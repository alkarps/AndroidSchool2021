package ru.alkarps.android.school2021.hw16

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.alkarps.android.school2021.hw16.client.RestClient
import ru.alkarps.android.school2021.hw16.client.impl.OkRestClient
import ru.alkarps.android.school2021.hw16.client.impl.UrlRestClient

class MainActivity : AppCompatActivity() {
    private val restClientHandler = Handler(
        HandlerThread("background").apply { start() }.looper
    )
    private lateinit var client: RestClient
    private lateinit var responseView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        client = UrlRestClient()
        responseView = findViewById(R.id.response)
        responseView.movementMethod = ScrollingMovementMethod()
        findViewById<RadioGroup>(R.id.client_choice).setOnCheckedChangeListener { _, currentId ->
            client = if (currentId == R.id.client_choice_url) UrlRestClient() else OkRestClient()
        }
        findViewById<Button>(R.id.get).setOnClickListener { restClientHandler.post { doGet() } }
        findViewById<Button>(R.id.post).setOnClickListener { restClientHandler.post { doPost() } }
    }

    private fun doGet() {
        doCall { client.doGet() }
    }

    private fun doPost() {
        doCall { client.doPost() }
    }

    private fun doCall(call: () -> String) {
        val response = call()
        this.runOnUiThread { responseView.text = response }
    }
}