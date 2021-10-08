package ru.alkarps.android.school2021.hw15.timer

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.R
import ru.alkarps.android.school2021.hw15.TimerApi

class TimerDisplayFragment : Fragment(R.layout.timer_display_fragment_layout) {
    private val timerHandler: Handler
    private var displayTime: TextView? = null
    private var currentTime = -1

    init {
        val thread = HandlerThread("timer")
        thread.start()
        timerHandler = Handler(thread.looper)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displayTime = view.findViewById(R.id.timer_display)
        currentTime = arguments?.getInt(TIME_KEY) ?: -1
        updateDisplayTime()
    }

    override fun onDestroy() {
        super.onDestroy()
        timerHandler.removeCallbacksAndMessages(null)
        displayTime = null
    }

    override fun onStart() {
        super.onStart()
        updateCurrentTime()
    }

    private fun updateCurrentTime() {
        if (currentTime > 0) {
            currentTime--
            displayTime?.post { updateDisplayTime() }
            timerHandler.postDelayed({ updateCurrentTime() }, 1000L)
        } else {
            Looper.myLooper()?.quitSafely()
            displayTime?.post { returnControlPanel() }
        }
    }

    private fun returnControlPanel() {
        (activity as TimerApi).stop()
    }

    private fun updateDisplayTime() {
        displayTime!!.text = currentTime.toString()
    }

    companion object {
        private const val TIME_KEY = "TimeKey"
        fun newInstance(startTime: Int): TimerDisplayFragment = TimerDisplayFragment().apply {
            arguments = bundleOf(TIME_KEY to startTime)
        }
    }
}