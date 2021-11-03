package ru.alkarps.android.school2021.hw25.timer

import android.util.Log
import java.lang.ref.WeakReference
import java.util.Timer
import java.util.concurrent.atomic.AtomicLong

class Timer(listener: Listener) {
    private val listener = WeakReference(listener)
    private val value = AtomicLong(0)
    private var timer = Timer()
    var status = Status.STOPPED
        private set

    fun getCurrentValue(): String = value.get().toString()

    fun start() {
        if (status != Status.STARTED) {
            timer = Timer()
            timer.scheduleAtFixedRate(TimerTask(), 1000L, 1000L)
            status = Status.STARTED
        }
    }

    fun stop() {
        stopTimer(Status.STOPPED)
    }

    private fun stopTimer(status: Status) {
        if (this.status == Status.STARTED) timer.cancel()
        this.status = status
        if (status == Status.STOPPED) value.set(0)
        listener.get()?.onTick(value.get())
    }

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

    interface Listener {
        fun onTick(value: Long)
    }

    enum class Status {
        STARTED, PAUSED, STOPPED
    }

    companion object {
        private const val TAG = "Timer"
    }
}