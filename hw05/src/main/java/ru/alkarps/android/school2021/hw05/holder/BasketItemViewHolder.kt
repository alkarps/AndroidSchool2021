package ru.alkarps.android.school2021.hw05.holder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import ru.alkarps.android.school2021.hw05.R
import ru.alkarps.android.school2021.hw05.model.Basket
import ru.alkarps.android.school2021.hw05.model.BasketItem

class BasketItemViewHolder(
    itemView: View,
    listener: BasketListener
) : BasketViewHolder(itemView, listener) {

    private val image: ImageView = itemView.findViewById(R.id.basket_image)
    private val button: Button = itemView.findViewById(R.id.add_apple)

    override fun onBind(item: BasketItem) {
        val basket = item as Basket
        image.setImageLevel(basket.getImageLevel())
        button.setOnClickListener {
            listener.addApple(item)
            image.setImageLevel(basket.getImageLevel())
        }
    }
}