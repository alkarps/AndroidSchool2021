package ru.alkarps.android.school2021.hw15.timer.display

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import ru.alkarps.android.school2021.hw15.timer.TimerDisplayFragment

class HandlerTimerDisplayFragment : TimerDisplayFragment() {
    private val timerHandler: Handler = Handler(
        HandlerThread("timer").apply { this.start() }.looper
    )

    override fun onStart() {
        super.onStart()
        updateCurrentTime()
    }

    override fun onDestroy() {
        super.onDestroy()
        timerHandler.removeCallbacksAndMessages(null)
    }

    override fun updateDisplayAndSendNextMessage() {
        displayTime.post { updateDisplayTime() }
        timerHandler.postDelayed({ updateCurrentTime() }, 1000L)
        Log.i(TAG, "Finish updating current time with new value = $currentTime")
    }

    override fun exiting() {
        Looper.myLooper()?.quitSafely()
        Log.i(TAG, "Run exiting")
        displayTime.post { returnControlPanel() }
    }
}