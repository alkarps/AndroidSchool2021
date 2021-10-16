package ru.alkarps.android.school2021.hw13.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw13.R

class TextFragment : Fragment(R.layout.text_fragment_layout) {
    private lateinit var text: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text = view.findViewById(R.id.text_view)
        text.text = arguments?.getString(TEXT_KEY)
    }

    fun setText(text: String) {
        this.text.text = text
    }

    companion object {
        private const val TEXT_KEY = "TEXT_KEY"

        fun newInstance(text: String): TextFragment {
            return TextFragment().apply {
                arguments = bundleOf(TEXT_KEY to text)
            }
        }
    }
}