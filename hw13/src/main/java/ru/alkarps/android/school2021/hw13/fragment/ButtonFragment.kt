package ru.alkarps.android.school2021.hw13.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw13.R

class ButtonFragment : Fragment(R.layout.button_fragment_layout) {
    private lateinit var button: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button = view.findViewById(R.id.button)
    }

    companion object {
        fun newInstance() = ButtonFragment()
    }
}