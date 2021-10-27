package ru.alkarps.android.school2021.hw16

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.alkarps.android.school2021.hw16.client.RestClient

class MainActivity : AppCompatActivity() {
    private lateinit var subscriber: Disposable
    private lateinit var client: RestClient
    private lateinit var responseView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        client = RestClient.createClient(R.id.client_choice_url)
        responseView = findViewById(R.id.response)
        responseView.movementMethod = ScrollingMovementMethod()
        findViewById<RadioGroup>(R.id.client_choice).setOnCheckedChangeListener { _, currentId ->
            client = RestClient.createClient(currentId)
        }
        findViewById<Button>(R.id.get).setOnClickListener { doGet() }
        findViewById<Button>(R.id.post).setOnClickListener { doPost() }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriber.dispose()
    }

    private fun doGet() {
        doCall { client.doGet() }
    }

    private fun doPost() {
        doCall { client.doPost() }
    }

    private fun doCall(call: () -> String) {
        subscriber = Single.fromCallable(call)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { responseView.text = it }
            .subscribe()
    }
}