package ru.alkarps.android.school2021.hw05.holder

import android.view.View
import android.widget.Button
import ru.alkarps.android.school2021.hw05.R
import ru.alkarps.android.school2021.hw05.model.BasketItem

class AppleItemViewHolder(
    itemView: View,
    listener: BasketListener
) : BasketViewHolder(itemView, listener) {

    private val button: Button = itemView.findViewById(R.id.remove_apple)
    override fun onBind(item: BasketItem) {
        button.setOnClickListener { listener.removeApple(item) }
    }
}