package ru.alkarps.android.school2021.hw25

import android.app.*
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.text.SimpleDateFormat
import java.util.*

class ForegroundService : Service() {
    var onForeground = false
        private set

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            Log.i(TAG, "${it.action}")
            when (it.action) {
                START_INTENT -> {
                    startForeground(NOTIFICATION_ID, createNotification())
                    onForeground = true
                }
                STOP_INTENT -> {
                    stopForeground(true)
                    stopSelf()
                    onForeground = false
                }
            }
        }
        return START_NOT_STICKY
    }

    private fun createNotification(): Notification {
        createNotificationChannel()
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_timer_24)
            .setContentTitle(getString(R.string.fg_notification_title))
            .setContentText("Текущее время: ${currentTime()}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(stopNotificationAction())
            .setAutoCancel(true)
            .build()
    }

    private fun currentTime(): String =
        SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.fg_notification_channel_name)
            val description = getString(R.string.fg_notification_channel_description)
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return LocalBinder()
    }

    private fun stopNotificationAction(): NotificationCompat.Action =
        NotificationCompat.Action(R.drawable.ic_sharp_power_off_24, "Stop service", stopIntent())

    private fun stopIntent(): PendingIntent = newIntent(STOP_INTENT)

    private fun newIntent(action: String): PendingIntent {
        val intent = Intent(this, ForegroundService::class.java).apply { this.action = action }
        return PendingIntent.getService(this, 0, intent, 0)
    }

    inner class LocalBinder : Binder() {
        fun getService(): ForegroundService = this@ForegroundService
    }

    companion object {
        private const val NOTIFICATION_ID = 42
        private const val CHANNEL_ID = "ForegroundChannelId"
        private const val TAG = "ForegroundService"
        const val START_INTENT = "ru.alkarps.android.school2021.hw25.ForegroundService.START_INTENT"
        const val STOP_INTENT = "ru.alkarps.android.school2021.hw25.ForegroundService.STOP_INTENT"
    }
}