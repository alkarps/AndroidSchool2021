package ru.alkarps.android.school2021.hw06.converter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw06.R
import ru.alkarps.android.school2021.hw06.model.ConverterValue
import ru.alkarps.android.school2021.hw06.model.Quantity

class ConverterAdapter(
    quantity: Quantity,
    private val updateInBackground: (DiffUtil.DiffResult, ConverterAdapter) -> Unit
) :
    RecyclerView.Adapter<ConverterAdapter.Holder>() {
    private val converter = Converter()
    private var values: List<ConverterValue> = converter.startList(quantity.units)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.converter_item_layout, parent, false)
        return Holder(view, this::setFirst, this::updateValues)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(values[position])
    }

    override fun getItemCount(): Int = values.size

    private fun setFirst(mustBeFirst: ConverterValue) {
        val new = values.toMutableList()
        new.remove(mustBeFirst)
        new.add(0, mustBeFirst)
        update(new)
    }

    private fun updateValues() {
        val new = converter.convert(values)
        update(new)
    }

    private fun update(new: List<ConverterValue>) {
        val callback = ConverterDiffUtilCallback(values, new)
        val diff = DiffUtil.calculateDiff(callback)
        values = new
        updateInBackground(diff, this)
    }

    class Holder(
        itemView: View,
        private val toFirst: (ConverterValue) -> Unit,
        private val updateOnChange: () -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val label: TextView = itemView.findViewById(R.id.converter_item_label)
        private val edit: EditText = itemView.findViewById(R.id.converter_item_edit)

        fun onBind(value: ConverterValue) {
            label.setText(value.unit.label)
            edit.setText(value.value.toBigDecimal().toPlainString())
            edit.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) toFirst(value)
            }
            edit.doOnTextChanged { text, start, before, count ->
                value.value = text?.toString()?.apply {
                    if (this.last() == 'E') substring(0, length)
                }?.toDoubleOrNull() ?: 0.0
                updateOnChange()
            }
        }
    }

    class ConverterDiffUtilCallback(
        private val old: List<ConverterValue>,
        private val new: List<ConverterValue>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition] == new[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition] == new[newItemPosition]
    }
}