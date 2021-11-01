package ru.alkarps.android.school2021.hw25

import android.app.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import java.util.*

class ForegroundService : Service(), PhoneStatusReceiver.Listener {
    var onForeground = false
        private set

    private lateinit var phoneStatusReceiver: PhoneStatusReceiver
    private lateinit var phoneState: PhoneStatusReceiver.PhoneState

    override fun onCreate() {
        super.onCreate()
        phoneStatusReceiver = PhoneStatusReceiver(this)
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED).apply {
            addAction(Intent.ACTION_TIME_TICK)
        }
        registerReceiver(phoneStatusReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(phoneStatusReceiver)
    }

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

    override fun onChange(phoneState: PhoneStatusReceiver.PhoneState) {
        this.phoneState = phoneState
        if (onForeground) startForeground(NOTIFICATION_ID, createNotification())
    }

    private fun createNotification(): Notification {
        createNotificationChannel()
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_timer_24)
            .setContentTitle(getString(R.string.fg_notification_title))
            .setCustomContentView(createRemoveView(R.layout.small_notification))
            .setCustomBigContentView(createRemoveView(R.layout.big_notification))
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
    }

    private fun createRemoveView(layoutId: Int): RemoteViews {
        val view = RemoteViews(packageName, layoutId)
        view.setTextViewText(R.id.battery_level, phoneState.batteryLevel.toString())
        view.setTextViewText(R.id.current_time, phoneState.systemTime)
        view.setImageViewResource(
            R.id.battery_charging,
            if (phoneState.batteryCharging) R.drawable.ic_baseline_power_24
            else R.drawable.ic_sharp_power_off_24
        )
        view.setOnClickPendingIntent(R.id.stop_service, stopIntent())
        return view
    }

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