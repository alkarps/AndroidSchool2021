package ru.alkarps.android.school2021.hw15.timer.concurrency.impl

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import ru.alkarps.android.school2021.hw15.timer.concurrency.ConcurrencyTimerEngine

class HandlerConcurrencyTimerEngine(update: () -> Unit) : ConcurrencyTimerEngine(update) {
    private val timerHandler: Handler = Handler(
        HandlerThread("timer").apply { this.start() }.looper
    )

    override fun startThreads() {
        update()
    }

    override fun destroyThreads() {
        timerHandler.removeCallbacksAndMessages(null)
    }

    override fun sendNextMessage() {
        timerHandler.postDelayed(update, 1000L)
    }

    override fun finishingIfTimeOut() {
        Looper.myLooper()?.quitSafely()
    }
}