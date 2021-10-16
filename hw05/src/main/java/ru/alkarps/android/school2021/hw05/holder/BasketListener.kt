package ru.alkarps.android.school2021.hw05.holder

import ru.alkarps.android.school2021.hw05.model.BasketItem

interface BasketListener {
    fun addApple(basket: BasketItem)
    fun removeApple(apple: BasketItem)
}