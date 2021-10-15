package ru.alkarps.android.school2021.hw15.timer.concurrency

import ru.alkarps.android.school2021.hw15.R
import ru.alkarps.android.school2021.hw15.timer.concurrency.impl.HandlerConcurrencyTimerEngine
import ru.alkarps.android.school2021.hw15.timer.concurrency.impl.RxConcurrencyTimerEngine
import ru.alkarps.android.school2021.hw15.timer.concurrency.impl.ScheduledExecutorConcurrencyTimerEngine

abstract class ConcurrencyTimerEngine(protected val update: () -> Unit) {
    abstract fun startThreads()
    abstract fun destroyThreads()
    abstract fun sendNextMessage()
    abstract fun finishingIfTimeOut()

    enum class Type(
        private val id: Int,
        private val _init: (() -> Unit) -> ConcurrencyTimerEngine
    ) {
        HANDLER(R.id.set_up_thread_service_handler, ::HandlerConcurrencyTimerEngine),
        SCHEDULED(R.id.set_up_thread_service_scheduler, ::ScheduledExecutorConcurrencyTimerEngine),
        RX(R.id.set_up_thread_service_rx, ::RxConcurrencyTimerEngine);

        fun init(update: () -> Unit) = _init(update)

        companion object {
            fun findById(id: Int) =
                values().find { it.id == id } ?: throw IllegalArgumentException()
        }
    }
}