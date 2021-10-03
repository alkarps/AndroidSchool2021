package ru.alkarps.android.school2021.hw05.model

import ru.alkarps.android.school2021.hw05.model.BasketItem.Companion.APPLE_TYPE

class Apple : BasketItem {
    override fun getTypeId(): Int = APPLE_TYPE
}