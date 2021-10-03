package ru.alkarps.android.school2021.hw05.model

import ru.alkarps.android.school2021.hw05.model.BasketItem.Companion.COUNTER_TYPE

class Counter : BasketItem {
    private var applesCount: Int = 0

    override fun getTypeId(): Int = COUNTER_TYPE

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
