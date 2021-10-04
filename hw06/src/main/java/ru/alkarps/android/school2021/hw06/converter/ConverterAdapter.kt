package ru.alkarps.android.school2021.hw06.converter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw06.R
import ru.alkarps.android.school2021.hw06.model.ConverterValue
import ru.alkarps.android.school2021.hw06.model.Quantity

class ConverterAdapter(
    quantity: Quantity,
    private val updateInBackground: (DiffUtil.DiffResult, ConverterAdapter) -> Unit
) : RecyclerView.Adapter<ConverterViewHolder>() {
    private val converter = Converter()
    private var values: List<ConverterValue> = converter.startList(quantity.units)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.converter_item_layout, parent, false)
        return ConverterViewHolder(view, this::setFirst, this::updateValues)
    }

    override fun onBindViewHolder(holder: ConverterViewHolder, position: Int) {
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
}