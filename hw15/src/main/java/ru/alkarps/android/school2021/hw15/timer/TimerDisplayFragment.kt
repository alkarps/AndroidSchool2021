package ru.alkarps.android.school2021.hw15.timer

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.R

abstract class TimerDisplayFragment : Fragment(R.layout.timer_display_fragment_layout) {
    private var displayTime: TextView? = null
    private var currentTime = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displayTime = view.findViewById(R.id.timer_display)
        currentTime = arguments?.getInt(TIME_KEY) ?: -1
        updateDisplayTime()
    }

    override fun onStart() {
        super.onStart()
        startThreads()
    }

    abstract fun startThreads()

    override fun onDestroy() {
        super.onDestroy()
        destroyThreads()
        displayTime = null
    }

    abstract fun destroyThreads()

    protected fun updateCurrentTime() {
        if (currentTime > 0) {
            currentTime--
            displayTime?.post { updateDisplayTime() }
            sendNextMessage()
        } else {
            finishingIfTimeOut()
            displayTime?.post { returnControlPanel() }
        }
    }

    abstract fun sendNextMessage()

    abstract fun finishingIfTimeOut()

    private fun returnControlPanel() {
        (activity as TimerApi).stop()
    }

    private fun updateDisplayTime() {
        displayTime!!.text = currentTime.toString()
    }

    companion object {
        const val TIME_KEY = "TimeKey"
    }
}