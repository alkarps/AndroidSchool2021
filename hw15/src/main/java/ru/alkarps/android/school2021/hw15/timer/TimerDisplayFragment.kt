package ru.alkarps.android.school2021.hw15.timer

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.R

class TimerDisplayFragment : Fragment(R.layout.timer_display_fragment_layout) {
    private lateinit var displayTime: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displayTime = view.findViewById(R.id.timer_display)
    }

    fun updateDisplayedTime(newTime: Int) {
        displayTime.text = newTime.toString()
    }

    companion object {
        fun newInstance(): TimerDisplayFragment = TimerDisplayFragment()
    }
}