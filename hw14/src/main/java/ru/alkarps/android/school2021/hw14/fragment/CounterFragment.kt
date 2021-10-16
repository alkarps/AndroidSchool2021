package ru.alkarps.android.school2021.hw14.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw14.R

class CounterFragment : Fragment(R.layout.counter_fragment_layout) {
    private lateinit var counter: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        counter = view.findViewById(R.id.counter)
        counter.text = "0"
    }

    fun updateCounter(currentCounter: Int) {
        counter.text = currentCounter.toString()
    }
}