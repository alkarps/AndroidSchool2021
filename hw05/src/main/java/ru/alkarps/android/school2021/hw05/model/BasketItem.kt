package ru.alkarps.android.school2021.hw05.model

sealed interface BasketItem {
    fun getTypeId(): Int

    companion object {
        const val BASKET_TYPE = 0
        const val APPLE_TYPE = 1
        const val COUNTER_TYPE = 2
    }
}