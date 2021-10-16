package ru.alkarps.android.school2021.hw14.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw14.OperationApi
import ru.alkarps.android.school2021.hw14.R

class ControlFragment : Fragment(R.layout.control_fragment_layout) {
    private lateinit var addToBackstack: CheckBox
    private lateinit var operationCommit: RadioGroup
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val operationApi = activity as OperationApi
        addToBackstack = view.findViewById(R.id.add_to_backstack_flag)
        operationCommit = view.findViewById(R.id.commit_operation)
        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            operationApi.addTransaction(
                operationCommit.checkedRadioButtonId == R.id.commit_operation_add,
                addToBackstack.isChecked
            )
        }
        view.findViewById<Button>(R.id.remove_button).setOnClickListener {
            operationApi.removeTransaction()
        }
    }
}