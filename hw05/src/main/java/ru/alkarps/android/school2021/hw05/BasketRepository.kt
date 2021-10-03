package ru.alkarps.android.school2021.hw05

import ru.alkarps.android.school2021.hw05.model.Apple
import ru.alkarps.android.school2021.hw05.model.Basket
import ru.alkarps.android.school2021.hw05.model.BasketItem
import ru.alkarps.android.school2021.hw05.model.Counter

class BasketRepository(private val submitList: (List<BasketItem>) -> Unit) {
    private val baskets: MutableList<BasketItem> = mutableListOf(Counter())

    init {
        submit()
    }

    private fun submit() {
        if (baskets.size > 1) {
            baskets.removeLast()
            val applesCount = baskets.count { it is Apple }
            baskets.add(Counter(applesCount = applesCount))
        }
        submitList.invoke(baskets.toList())
    }

    fun addBasket() {
        baskets.add(baskets.size - 1, Basket())
        submit()
    }

    fun addApple(basket: BasketItem): Boolean {
        val position = baskets.indexOf(basket as Basket)
        if (basket.apples < 3) {
            baskets.add(position + 1, Apple(basket = basket))
            basket.apples++
            submit()
            return true
        }
        return false
    }

    fun removeApple(apple: BasketItem) {
        (apple as Apple).basket.apples--
        baskets.remove(apple)
        submit()
    }

    fun removeAllBaskets() {
        baskets.clear()
        baskets.add(Counter())
        submit()
    }
}