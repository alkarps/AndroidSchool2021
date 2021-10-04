package ru.alkarps.android.school2021.hw06.converter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw06.R
import ru.alkarps.android.school2021.hw06.model.ConverterValue

class ConverterAdapter(private var values: List<ConverterValue>) :
    RecyclerView.Adapter<ConverterAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.converter_item_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(values[position])
    }

    override fun getItemCount(): Int = values.size

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val label: TextView = itemView.findViewById(R.id.converter_item_label)
        private val edit: EditText = itemView.findViewById(R.id.converter_item_edit)

        fun onBind(value: ConverterValue) {
            label.setText(value.unit.label)
            edit.setText(value.value.toString())
        }
    }
}