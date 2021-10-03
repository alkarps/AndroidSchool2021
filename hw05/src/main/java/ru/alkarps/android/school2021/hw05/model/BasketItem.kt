package ru.alkarps.android.school2021.hw05.model

sealed interface BasketItem {
    fun getId(): String
    fun getTypeId(): Int
}