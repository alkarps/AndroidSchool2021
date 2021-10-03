package ru.alkarps.android.school2021.hw05.model

import ru.alkarps.android.school2021.hw05.holder.ViewType

class Apple : BasketItem {
    override fun getTypeId(): Int = ViewType.APPLE.getTypeId()
}