package ru.alkarps.android.school2021.hw15.timer.display

import android.util.Log
import ru.alkarps.android.school2021.hw15.timer.TimerDisplayFragment
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class ScheduledTimerDisplayFragment : TimerDisplayFragment() {
    private val service = Executors.newSingleThreadScheduledExecutor()
    private lateinit var task: Future<*>

    override fun onStart() {
        super.onStart()
        task = service.scheduleAtFixedRate({ updateCurrentTime() }, 0, 1, TimeUnit.SECONDS)
    }

    override fun onDestroy() {
        super.onDestroy()
        service.shutdownNow()
    }

    override fun updateDisplayAndSendNextMessage() {
        displayTime.post { updateDisplayTime() }
        Log.i(TAG, "Finish updating current time with new value = $currentTime")
    }

    override fun exiting() {
        task.cancel(true)
        Log.i(TAG, "Run exiting")
        displayTime.post { returnControlPanel() }
    }
}