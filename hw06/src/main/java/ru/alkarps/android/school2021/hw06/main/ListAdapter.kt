package ru.alkarps.android.school2021.hw06.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw06.R
import ru.alkarps.android.school2021.hw06.model.Quantity

class ListAdapter(
    private val quantities: List<Quantity>
) : RecyclerView.Adapter<ListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(quantities[position % quantities.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(R.id.list_item_label)

        fun onBind(quantity: Quantity) {
            text.setText(quantity.label)
        }
    }
}