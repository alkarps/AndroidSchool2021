package ru.alkarps.android.school2021.hw21

import android.content.IntentFilter
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ru.alkarps.android.school2021.hw21.broadcast.SmsTextBroadcast
import ru.alkarps.android.school2021.hw21.broadcast.SmsTextListener
import ru.alkarps.android.school2021.hw21.code.CodeExtractor
import ru.alkarps.android.school2021.hw21.code.RegexCodeExtractor

class MainActivity : AppCompatActivity(), SmsTextListener {
    private lateinit var smsTextInput: EditText
    private lateinit var codeExtractor: CodeExtractor
    private lateinit var smsBroadcastReceiver: SmsTextBroadcast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter().apply {
            priority = 99999999
            addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        }
        smsBroadcastReceiver = SmsTextBroadcast(this)
        registerReceiver(smsBroadcastReceiver, filter)

        smsTextInput = findViewById(R.id.code_input)
        codeExtractor = RegexCodeExtractor()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(smsBroadcastReceiver)
    }

    override fun onGettingSmsText(text: String) {
        Log.i(TAG, "getting sms text: $text")
        smsTextInput.setText(codeExtractor.getCode(text))
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}