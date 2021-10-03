package ru.alkarps.android.school2021.hw05.model

import ru.alkarps.android.school2021.hw05.holder.ViewType

class Counter : BasketItem {
    var applesCount: Int = 0

    override fun getTypeId(): Int = ViewType.COUNTER.getTypeId()
}
