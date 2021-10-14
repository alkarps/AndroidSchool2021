package ru.alkarps.android.school2021.hw15.timer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.R
import java.util.concurrent.atomic.AtomicInteger

abstract class TimerDisplayFragment(
    private val logTag: String = "TimerDisplayFragment"
) : Fragment(R.layout.timer_display_fragment_layout) {
    private lateinit var displayTime: TextView
    private var currentTime = AtomicInteger(-1)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(logTag, "View created")
        currentTime = AtomicInteger(arguments?.getInt(TIME_KEY) ?: -1)
        displayTime = view.findViewById(R.id.timer_display)
        updateDisplayTime()
        view.findViewById<Button>(R.id.timer_exit).setOnClickListener { exiting() }
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
    }

    abstract fun destroyThreads()

    protected fun updateCurrentTime() {
        Log.i(logTag, "Start updating current time with value = $currentTime")
        if (currentTime.get() > 0) {
            currentTime.decrementAndGet()
            displayTime.post { updateDisplayTime() }
            sendNextMessage()
            Log.i(logTag, "Finish updating current time with new value = $currentTime")
        } else {
            Log.i(logTag, "Ending updating current time and start exit")
            finishingIfTimeOut()
            exiting()
        }
    }

    private fun exiting() {
        Log.i(logTag, "Run exiting")
        displayTime.post { returnControlPanel() }
    }

    abstract fun sendNextMessage()

    abstract fun finishingIfTimeOut()

    private fun returnControlPanel() {
        Log.i(logTag, "Start exiting")
        (activity as TimerApi).stop()
    }

    private fun updateDisplayTime() {
        Log.i(logTag, "Updating display time with value = $currentTime")
        displayTime.text = "$currentTime"
    }

    companion object {
        const val TIME_KEY = "TimeKey"
    }
}