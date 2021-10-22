package ru.alkarps.android.school2021.hw21.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
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
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            Log.i(TAG, "startGettingSms")
            intent?.extras?.apply {
                val format = getString(SMS_FORMAT)
                (get(SMS_PDUS) as Array<ByteArray?>)
                    .map { createSmsMessage(it, format) }
                    .filterNotNull()
                    .map { it.messageBody }
                    .forEach { listener.get()?.onGettingSmsText(it) }
            }
        } catch (e: RuntimeException) {
            Log.i(TAG, e.message, e)
        }
    }

    private fun createSmsMessage(pdus: ByteArray?, format: String?): SmsMessage? {
        return if (pdus == null) null
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (format == null) null else SmsMessage.createFromPdu(pdus, format)
        } else SmsMessage.createFromPdu(pdus)
    }

    companion object {
        private const val SMS_FORMAT = "format"
        private const val SMS_PDUS = "pdus"
        private const val TAG = "SmsTextBroadcast"
    }
}