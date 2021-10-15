package ru.alkarps.android.school2021.hw16.concurrency

import ru.alkarps.android.school2021.hw16.R
import ru.alkarps.android.school2021.hw16.concurrency.impl.HandlerConcurrencyEngine
import ru.alkarps.android.school2021.hw16.concurrency.impl.RxConcurrencyEngine

interface ConcurrencyEngine {
    fun doJob(job: () -> Unit)
    fun destroy()

    companion object {
        fun createClient(id: Int): ConcurrencyEngine =
            if (id == R.id.concurrency_choice_handler) HandlerConcurrencyEngine()
            else RxConcurrencyEngine()
    }
}