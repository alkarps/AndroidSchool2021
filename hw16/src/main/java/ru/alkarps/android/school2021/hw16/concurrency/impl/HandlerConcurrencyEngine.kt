package ru.alkarps.android.school2021.hw16.concurrency.impl

import android.os.Handler
import android.os.HandlerThread
import ru.alkarps.android.school2021.hw16.concurrency.ConcurrencyEngine

class HandlerConcurrencyEngine : ConcurrencyEngine {
    private val restClientHandler = Handler(
        HandlerThread("background").apply { start() }.looper
    )

    override fun doJob(job: () -> Unit) {
        restClientHandler.post(job)
    }

    override fun destroy() {
        restClientHandler.removeCallbacksAndMessages(null)
    }
}