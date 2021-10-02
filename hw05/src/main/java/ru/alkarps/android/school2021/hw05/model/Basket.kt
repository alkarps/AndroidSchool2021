package ru.alkarps.android.school2021.hw05.model

class Basket {
    private var apples = 0

    fun addApple() {
        if (apples < 3) apples++
    }

    fun removeApple() {
        if (apples > 0) apples--
    }

    fun getImageLevel(): Int = (apples.toFloat() / 3 * 10000).toInt()
}