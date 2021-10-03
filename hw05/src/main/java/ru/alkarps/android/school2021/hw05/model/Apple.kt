package ru.alkarps.android.school2021.hw05.model

import kotlinx.parcelize.Parcelize
import ru.alkarps.android.school2021.hw05.holder.ViewType
import java.util.*
@Parcelize
data class Apple(
    private val id: String = UUID.randomUUID().toString(),
    val basket: Basket
) : BasketItem {
    override fun getId(): String = id
    override fun getTypeId(): Int = ViewType.APPLE.getTypeId()
}