package ru.alkarps.android.school2021.hw16.concurrency

interface ConcurrencyEngine {
    fun doJob(job: () -> Unit)
    fun destroy()
}