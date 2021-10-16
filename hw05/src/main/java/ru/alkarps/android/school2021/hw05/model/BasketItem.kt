package ru.alkarps.android.school2021.hw05.model

import android.os.Parcelable

sealed interface BasketItem : Parcelable {
    fun getId(): String
    fun getTypeId(): Int
}