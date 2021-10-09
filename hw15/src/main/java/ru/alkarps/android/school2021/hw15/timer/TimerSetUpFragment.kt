package ru.alkarps.android.school2021.hw15.timer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.R
import ru.alkarps.android.school2021.hw15.timer.display.HandlerTimerDisplayFragment

class TimerSetUpFragment : Fragment(R.layout.timer_set_up_fragment_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val timerApi = activity as TimerApi
        val startValueEdit = view.findViewById<EditText>(R.id.set_up_time)
        startValueEdit.setText("0")
        view.findViewById<Button>(R.id.start_timer).setOnClickListener {
            val startTime = startValueEdit.text.toString().toIntOrNull() ?: 0
            if (startTime > 0) {
                timerApi.start(HandlerTimerDisplayFragment.newInstance(startTime))
            } else {
                startValueEdit.setText("0")
            }
        }
    }

    companion object {
        fun newInstance(): TimerSetUpFragment = TimerSetUpFragment()
    }
}