package ru.alkarps.android.school2021.hw05.model

import ru.alkarps.android.school2021.hw05.holder.ViewType
import java.util.*

data class Apple(
    private val id: String = UUID.randomUUID().toString()
) : BasketItem {
    override fun getId(): String = id
    override fun getTypeId(): Int = ViewType.APPLE.getTypeId()
}