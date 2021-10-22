package ru.alkarps.android.school2021.hw21.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import java.lang.ref.WeakReference

/**
 * Броадкаст для получения текста пришедшей СМС
 */
class SmsTextBroadcast(
    listener: SmsTextListener
) : BroadcastReceiver() {
    private val listener = WeakReference(listener)
    override fun onReceive(context: Context?, intent: Intent) {
        try {
            Log.i(TAG, "startGettingSms")
            intent.extras?.apply {
                val format = getString(SMS_FORMAT)
                if (format != null) (get(SMS_PDUS) as Array<ByteArray?>)
                    .mapNotNull { SmsMessage.createFromPdu(it, format) }
                    .map { it.messageBody }
                    .forEach { listener.get()?.onGettingSmsText(it) }
            }
        } catch (e: RuntimeException) {
            Log.i(TAG, e.message, e)
        }
    }

    companion object {
        private const val SMS_FORMAT = "format"
        private const val SMS_PDUS = "pdus"
        private const val TAG = "SmsTextBroadcast"
    }
}