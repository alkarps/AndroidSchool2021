package ru.alkarps.android.school2021.hw05.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.alkarps.android.school2021.hw05.R

enum class ViewType(
    private val typeId: Int,
    private val layoutId: Int,
    private val holderConstructor: (View) -> BasketViewHolder
) {
    BASKET(0, R.layout.basket_item_layout, ::BasketItemViewHolder),
    APPLE(1, R.layout.apple_item_layout, ::AppleItemViewHolder),
    COUNTER(2, R.layout.counter_item_layout, ::CounterItemViewHolder);

    fun getTypeId() = typeId

    fun initHolder(parent: ViewGroup, basketListener: BasketListener): BasketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        val holder = holderConstructor.invoke(view)
        holder.setBasketListener(basketListener)
        return holder
    }

    companion object {
        fun getTypeById(id: Int) = values().find { it.typeId == id }
            ?: throw IllegalArgumentException("Unknown view type id")
    }
}