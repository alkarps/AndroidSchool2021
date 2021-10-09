package ru.alkarps.android.school2021.hw15.timer.display

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.core.os.bundleOf
import ru.alkarps.android.school2021.hw15.timer.TimerDisplayFragment

class HandlerTimerDisplayFragment : TimerDisplayFragment() {
    private val timerHandler: Handler = Handler(
        HandlerThread("timer").apply { this.start() }.looper
    )

    override fun startThreads() {
        updateCurrentTime()
    }

    override fun destroyThreads() {
        timerHandler.removeCallbacksAndMessages(null)
    }

    override fun sendNextMessage() {
        timerHandler.postDelayed({ updateCurrentTime() }, 1000L)
    }

    override fun finishingIfTimeOut() {
        Looper.myLooper()?.quitSafely()
    }

    companion object {
        fun newInstance(startTime: Int): HandlerTimerDisplayFragment =
            HandlerTimerDisplayFragment().apply {
                arguments = bundleOf(TIME_KEY to startTime)
            }
    }
}