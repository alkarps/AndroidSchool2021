package ru.alkarps.android.school2021.hw25.timer

import android.util.Log
import ru.alkarps.android.school2021.hw25.timer.Timer.Listener
import java.lang.ref.WeakReference
import java.util.Timer
import java.util.concurrent.atomic.AtomicLong

/**
 * Реализация таймера на основе [Timer]
 *
 * @param listener реализация интерфейса [Listener]
 *
 * @constructor новый экземпляр таймера
 */
class Timer(listener: Listener) {
    private val listener = WeakReference(listener)
    private val value = AtomicLong(0)
    private var timer = Timer()
    var status = Status.STOPPED
        private set

    /**
     * Метод получения текущего значения таймера
     *
     * @return текущее значение таймера
     */
    fun getCurrentValue(): String = value.get().toString()

    /**
     * Метод запуска таймера
     */
    fun start() {
        if (status != Status.STARTED) {
            timer = Timer()
            timer.scheduleAtFixedRate(TimerTask(), 1000L, 1000L)
            status = Status.STARTED
        }
    }

    /**
     * Метод остановки таймера
     */
    fun stop() {
        stopTimer(Status.STOPPED)
    }

    private fun stopTimer(status: Status) {
        if (this.status == Status.STARTED) timer.cancel()
        this.status = status
        if (status == Status.STOPPED) value.set(0)
        listener.get()?.onTick(value.get())
    }

    /**
     * Метод установки паузы таймера
     */
    fun pause() {
        stopTimer(Status.PAUSED)
    }

    private inner class TimerTask : java.util.TimerTask() {
        override fun run() {
            Log.i(TAG, "TimerTask running with ${value.get()}")
            val listener = listener.get()
            if (listener == null) stop()
            else listener.onTick(value.incrementAndGet())
        }
    }

    /**
     * Интерфейс для колбэк вызова при изменении таймера
     */
    interface Listener {
        fun onTick(value: Long)
    }

    /**
     * Статусы таймера
     */
    enum class Status {
        STARTED, PAUSED, STOPPED
    }

    companion object {
        private const val TAG = "Timer"
    }
}