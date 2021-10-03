package ru.alkarps.android.school2021.hw05.model

import kotlinx.parcelize.Parcelize
import ru.alkarps.android.school2021.hw05.holder.ViewType
import java.util.*
@Parcelize
data class Basket(
    private var id: String = UUID.randomUUID().toString(),
    var apples: Int = 0
) : BasketItem {
    override fun getId(): String = id
    override fun getTypeId(): Int = ViewType.BASKET.getTypeId()
    fun getImageLevel(): Int = (apples.toFloat() / 3 * 10000).toInt()
}