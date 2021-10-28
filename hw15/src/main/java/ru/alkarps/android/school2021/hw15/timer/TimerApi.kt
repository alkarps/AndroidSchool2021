package ru.alkarps.android.school2021.hw15.timer

interface TimerApi {
    fun start(displayFragment: TimerDisplayFragment)
    fun stop()
}