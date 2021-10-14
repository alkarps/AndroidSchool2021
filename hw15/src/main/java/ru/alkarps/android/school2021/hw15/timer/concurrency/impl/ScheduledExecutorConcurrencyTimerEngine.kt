package ru.alkarps.android.school2021.hw15.timer.concurrency.impl

import ru.alkarps.android.school2021.hw15.timer.concurrency.ConcurrencyTimerEngine
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class ScheduledExecutorConcurrencyTimerEngine(update: () -> Unit) : ConcurrencyTimerEngine(update) {
    private val service = Executors.newSingleThreadScheduledExecutor()
    private lateinit var task: Future<*>

    override fun startThreads() {
        task = service.scheduleAtFixedRate(update, 0, 1, TimeUnit.SECONDS)
    }

    override fun destroyThreads() {
        service.shutdownNow()
    }

    override fun sendNextMessage() {}

    override fun finishingIfTimeOut() {
        task.cancel(true)
    }
}