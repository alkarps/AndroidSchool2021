package ru.alkarps.android.school2021.hw05

import android.util.Log
import ru.alkarps.android.school2021.hw05.model.Apple
import ru.alkarps.android.school2021.hw05.model.Basket
import ru.alkarps.android.school2021.hw05.model.BasketItem

class BasketRepository(private val submitList: (List<BasketItem>) -> Unit) {
    private val baskets: MutableList<BasketItem> = mutableListOf(Basket())

    init {
        submit()
    }

    private fun submit() {
        submitList.invoke(baskets.toList())
    }

    fun getBasket() = baskets.toList()

    fun addBasket() {
        Log.e(TAG, "Calling addBasket")
        baskets.add(baskets.size - 1, Basket())
        submit()
    }

    fun addApple(position: Int) {
        baskets.add(position, Apple())
        submit()
    }

    fun removeApple(position: Int) {
        baskets.removeAt(position)
        submit()
    }

    fun removeAllBaskets() {
        val counter = baskets.last()
        baskets.clear()
        baskets.add(counter)
        submit()
    }

    companion object {
        const val TAG = "BasketRepository"
    }
}