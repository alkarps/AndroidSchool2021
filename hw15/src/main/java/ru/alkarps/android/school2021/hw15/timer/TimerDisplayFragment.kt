package ru.alkarps.android.school2021.hw15.timer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.R

abstract class TimerDisplayFragment(
    private val logTag: String = "TimerDisplayFragment"
) : Fragment(R.layout.timer_display_fragment_layout) {
    private var displayTime: TextView? = null
    private var currentTime = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(logTag, "View created")
        displayTime = view.findViewById(R.id.timer_display)
        currentTime = arguments?.getInt(TIME_KEY) ?: -1
        updateDisplayTime()
    }

    override fun onStart() {
        Log.i(logTag, "Start display fragment")
        super.onStart()
        startThreads()
    }

    abstract fun startThreads()

    override fun onDestroy() {
        Log.i(logTag, "Destroy display fragment")
        super.onDestroy()
        destroyThreads()
        displayTime = null
    }

    abstract fun destroyThreads()

    protected fun updateCurrentTime() {
        Log.i(logTag, "Start updating current time with value = $currentTime")
        if (currentTime > 0) {
            currentTime--
            displayTime?.post { updateDisplayTime() }
            sendNextMessage()
            Log.i(logTag, "Finish updating current time with new value = $currentTime")
        } else {
            finishingIfTimeOut()
            displayTime?.post { returnControlPanel() }
            Log.i(logTag, "Ending updating current time and start exit")
        }
    }

    abstract fun sendNextMessage()

    abstract fun finishingIfTimeOut()

    private fun returnControlPanel() {
        Log.i(logTag, "Start exiting")
        (activity as TimerApi).stop()
    }

    private fun updateDisplayTime() {
        Log.i(logTag, "Updating display time with value = $currentTime")
        displayTime!!.text = currentTime.toString()
    }

    companion object {
        const val TIME_KEY = "TimeKey"
    }
}