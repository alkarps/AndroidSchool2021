package ru.alkarps.android.school2021.hw15.timer.display

import androidx.core.os.bundleOf
import ru.alkarps.android.school2021.hw15.timer.TimerDisplayFragment
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ScheduledExecutorTimerDisplayFragment : TimerDisplayFragment() {
    private val service = Executors.newSingleThreadScheduledExecutor()
    override fun startThreads() {
        service.scheduleAtFixedRate({ updateCurrentTime() }, 0, 1, TimeUnit.SECONDS)
    }

    override fun destroyThreads() {
        service.shutdownNow()
    }

    override fun sendNextMessage() {}

    override fun finishingIfTimeOut() {
        service.shutdownNow()
    }

    companion object {
        fun newInstance(startTime: Int): ScheduledExecutorTimerDisplayFragment =
            ScheduledExecutorTimerDisplayFragment().apply {
                arguments = bundleOf(TIME_KEY to startTime)
            }
    }
}