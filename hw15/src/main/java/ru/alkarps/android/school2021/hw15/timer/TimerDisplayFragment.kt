package ru.alkarps.android.school2021.hw15.timer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.R
import ru.alkarps.android.school2021.hw15.timer.concurrency.ConcurrencyTimerEngine
import java.util.concurrent.atomic.AtomicInteger

class TimerDisplayFragment(
    concurrencyType: ConcurrencyTimerEngine.Type
) : Fragment(R.layout.timer_display_fragment_layout) {
    private val concurrencyTimerEngine = concurrencyType.init { updateCurrentTime() }
    private lateinit var displayTime: TextView
    private var currentTime = AtomicInteger(-1)

    init {
        Log.i(TAG, "Init TimerDisplayFragment with ${concurrencyType.name} engine")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "View created")
        currentTime = AtomicInteger(arguments?.getInt(TIME_KEY) ?: -1)
        displayTime = view.findViewById(R.id.timer_display)
        updateDisplayTime()
        view.findViewById<Button>(R.id.timer_exit).setOnClickListener { exiting() }
    }

    override fun onStart() {
        Log.i(TAG, "Start display fragment")
        super.onStart()
        concurrencyTimerEngine.startThreads()
    }

    override fun onDestroy() {
        Log.i(TAG, "Destroy display fragment")
        super.onDestroy()
        concurrencyTimerEngine.destroyThreads()
    }

    private fun updateCurrentTime() {
        Log.i(TAG, "Start updating current time with value = $currentTime")
        if (currentTime.get() > 0) {
            currentTime.decrementAndGet()
            displayTime.post { updateDisplayTime() }
            concurrencyTimerEngine.sendNextMessage()
            Log.i(TAG, "Finish updating current time with new value = $currentTime")
        } else {
            Log.i(TAG, "Ending updating current time and start exit")
            concurrencyTimerEngine.finishingIfTimeOut()
            exiting()
        }
    }

    private fun exiting() {
        Log.i(TAG, "Run exiting")
        displayTime.post { returnControlPanel() }
    }

    private fun returnControlPanel() {
        Log.i(TAG, "Start exiting")
        (activity as TimerApi).stop()
    }

    private fun updateDisplayTime() {
        Log.i(TAG, "Updating display time with value = $currentTime")
        displayTime.text = "$currentTime"
    }

    companion object {
        const val TAG = "TimerDisplayFragment"
        const val TIME_KEY = "TimeKey"

        fun newInstance(concurrencyType: ConcurrencyTimerEngine.Type, startTime: Int) =
            TimerDisplayFragment(concurrencyType).apply {
                arguments = bundleOf(TIME_KEY to startTime)
            }
    }
}