package ru.alkarps.android.school2021.hw05.model

import ru.alkarps.android.school2021.hw05.holder.ViewType

class Counter : BasketItem {
    private var applesCount: Int = 0

    override fun getTypeId(): Int = ViewType.COUNTER.getTypeId()

    fun resetCounter() {
        applesCount = 0
    }

    fun addApple() {
        applesCount++
    }

    fun removeApple(count: Int) {
        applesCount -= count
    }
}
