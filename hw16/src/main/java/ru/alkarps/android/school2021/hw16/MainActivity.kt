package ru.alkarps.android.school2021.hw16

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.alkarps.android.school2021.hw16.client.RestClient
import ru.alkarps.android.school2021.hw16.concurrency.ConcurrencyEngine

class MainActivity : AppCompatActivity() {
    private lateinit var concurrencyEngine: ConcurrencyEngine
    private lateinit var client: RestClient
    private lateinit var responseView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        client = RestClient.createClient(R.id.client_choice_url)
        concurrencyEngine = ConcurrencyEngine.createClient(R.id.concurrency_choice_handler)
        responseView = findViewById(R.id.response)
        responseView.movementMethod = ScrollingMovementMethod()
        findViewById<RadioGroup>(R.id.concurrency_choice).setOnCheckedChangeListener { _, currentId ->
            concurrencyEngine = ConcurrencyEngine.createClient(currentId)
        }
        findViewById<RadioGroup>(R.id.client_choice).setOnCheckedChangeListener { _, currentId ->
            client = RestClient.createClient(currentId)
        }
        findViewById<Button>(R.id.get).setOnClickListener { concurrencyEngine.doJob { doGet() } }
        findViewById<Button>(R.id.post).setOnClickListener { concurrencyEngine.doJob { doPost() } }
    }

    override fun onDestroy() {
        super.onDestroy()
        concurrencyEngine.destroy()
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