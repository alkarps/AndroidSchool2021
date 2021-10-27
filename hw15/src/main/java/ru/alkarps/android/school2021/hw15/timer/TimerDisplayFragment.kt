package ru.alkarps.android.school2021.hw15.timer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.R
import ru.alkarps.android.school2021.hw15.timer.display.HandlerTimerDisplayFragment
import ru.alkarps.android.school2021.hw15.timer.display.RxTimerDisplayFragment
import ru.alkarps.android.school2021.hw15.timer.display.ScheduledTimerDisplayFragment
import java.util.concurrent.atomic.AtomicInteger

abstract class TimerDisplayFragment : Fragment(R.layout.timer_display_fragment_layout) {
    protected lateinit var displayTime: TextView
    protected var currentTime = AtomicInteger(-1)

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
    }

    override fun onDestroy() {
        Log.i(TAG, "Destroy display fragment")
        super.onDestroy()
    }

    protected fun updateCurrentTime() {
        Log.i(TAG, "Start updating current time with value = $currentTime")
        if (currentTime.get() > 0) {
            currentTime.decrementAndGet()
            updateDisplayAndSendNextMessage()
        } else {
            Log.i(TAG, "Ending updating current time and start exit")
            exiting()
        }
    }

    abstract fun updateDisplayAndSendNextMessage()

    abstract fun exiting()

    protected fun returnControlPanel() {
        Log.i(TAG, "Start exiting")
        (activity as TimerApi).stop()
    }

    protected fun updateDisplayTime() {
        Log.i(TAG, "Updating display time with value = $currentTime")
        displayTime.text = "$currentTime"
    }

    enum class Type(
        private val id: Int,
        private val _init: () -> TimerDisplayFragment
    ) {
        HANDLER(R.id.set_up_thread_service_handler, ::HandlerTimerDisplayFragment),
        SCHEDULED(R.id.set_up_thread_service_scheduler, ::ScheduledTimerDisplayFragment),
        RX(R.id.set_up_thread_service_rx, ::RxTimerDisplayFragment);

        fun init(startTime: Int): TimerDisplayFragment {
            Log.i(TAG, "Init TimerDisplayFragment with $name engine")
            return _init().apply {
                arguments = bundleOf(TIME_KEY to startTime)
            }
        }

        companion object {
            fun findById(id: Int) =
                values().find { it.id == id } ?: throw IllegalArgumentException()
        }
    }

    companion object {
        const val TAG = "TimerDisplayFragment"
        const val TIME_KEY = "TimeKey"
    }
}