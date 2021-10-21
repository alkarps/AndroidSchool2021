package ru.alkarps.android.school2021.hw21.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.lang.ref.WeakReference

/**
 * Броадкаст для получения текста пришедшей СМС
 */
class SmsTextBroadcast(listener: SmsTextListener) : BroadcastReceiver() {
    private val listener = WeakReference(listener)
    override fun onReceive(context: Context?, intent: Intent?) {
        val text = intent?.extras
    }
}