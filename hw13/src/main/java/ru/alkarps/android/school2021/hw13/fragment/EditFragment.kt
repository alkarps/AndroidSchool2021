package ru.alkarps.android.school2021.hw13.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw13.PublicApi
import ru.alkarps.android.school2021.hw13.R

class EditFragment : Fragment(R.layout.edit_fragment_layout) {
    private lateinit var editor: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editor = view.findViewById(R.id.editor)
        val api = activity as PublicApi
        editor.addTextChangedListener { api.updateTextFragment(it.toString()) }
    }

    fun getText() = editor.text.toString()
}