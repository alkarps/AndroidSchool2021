package ru.alkarps.android.school2021.hw05.holder

import android.view.View
import android.widget.TextView
import ru.alkarps.android.school2021.hw05.R
import ru.alkarps.android.school2021.hw05.model.BasketItem
import ru.alkarps.android.school2021.hw05.model.Counter

class CounterItemViewHolder(itemView: View) : BasketViewHolder(itemView) {
    override fun onBind(position: Int, item: BasketItem) {
        val counter = (item as Counter).applesCount.toString()
        itemView.findViewById<TextView>(R.id.apple_counter).text = counter
    }

    override fun setBasketListener(listener: BasketListener) {}
}