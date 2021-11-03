package ru.alkarps.android.school2021.hw25

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import ru.alkarps.android.school2021.hw25.PhoneStatusReceiver.Listener
import ru.alkarps.android.school2021.hw25.PhoneStatusReceiver.PhoneState
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

/**
 * Ресивер для получения информации о статусе телефона
 *
 * @param listener реализация интерфейса [Listener]
 * @constructor Новый объект ресивера
 *
 * @see [PhoneState] информация о статусе телефона
 */
class PhoneStatusReceiver(listener: Listener) : BroadcastReceiver() {
    private val listener = WeakReference(listener)
    private lateinit var phoneState: PhoneState
    override fun onReceive(context: Context, intent: Intent) {
        phoneState =
            if (intent.action == Intent.ACTION_TIME_TICK) phoneState.copy(systemTime = currentTime())
            else PhoneState(getBatteryLevel(intent), isCharging(intent), currentTime())
        listener.get()?.onChange(phoneState)
    }

    private fun getBatteryLevel(intent: Intent): Int {
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        return (level / scale.toFloat() * 100.0F).toInt()
    }

    private fun isCharging(intent: Intent): Boolean {
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        return status == BatteryManager.BATTERY_STATUS_CHARGING
    }

    private fun currentTime(): String =
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

    /**
     * Интерфейс колбэк-вызова при изменении статуса телефона
     */
    interface Listener {
        fun onChange(phoneState: PhoneState)
    }

    /**
     * Информация о статусе телефона
     *
     * @property batteryLevel уровень заряда телефона
     * @property batteryCharging признак зарядки телефона
     * @property systemTime текущее время в формате HH:mm
     * @constructor новый объект информации о статусе телефона
     */
    data class PhoneState(
        val batteryLevel: Int,
        val batteryCharging: Boolean,
        val systemTime: String
    )
}