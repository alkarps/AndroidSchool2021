package ru.alkarps.android.school2021.hw29.presentation.providers

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersProvider {
    fun main(): Scheduler
    fun back(): Scheduler
}