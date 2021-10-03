package ru.alkarps.android.school2021.hw05.model

import ru.alkarps.android.school2021.hw05.holder.ViewType

class Basket : BasketItem {
    private var apples = 0

    override fun getTypeId(): Int = ViewType.BASKET.getTypeId()

    fun addApple() {
        if (apples < 3) apples++
    }

    fun removeApple() {
        if (apples > 0) apples--
    }

    fun getImageLevel(): Int = (apples.toFloat() / 3 * 10000).toInt()
}