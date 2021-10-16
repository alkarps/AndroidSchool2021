package ru.alkarps.android.school2021.hw06.converter

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw06.R
import ru.alkarps.android.school2021.hw06.model.QuantityValue

class ConverterViewHolder(
    itemView: View,
    private val toFirst: (QuantityValue) -> Unit,
    private val updateOnChange: () -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val label: TextView = itemView.findViewById(R.id.converter_item_label)
    private val edit: EditText = itemView.findViewById(R.id.converter_item_edit)

    fun onBind(value: QuantityValue) {
        label.setText(value.unit.label)
        edit.setText(value.value.toBigDecimal().toPlainString())
        edit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) toFirst(value)
        }
        edit.doOnTextChanged { text, _, _, _ ->
            value.value = if (text.isNullOrBlank()) 0.0
            else text.toString().apply {
                if (this.last() == 'E') substring(0, length)
            }.toDouble()
            updateOnChange()
        }
    }
}