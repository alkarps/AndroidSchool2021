package ru.alkarps.android.school2021.hw06.model

data class QuantityValue(val unit: QuantityUnit, var value: Double) {
    fun getBaseValue() = value * unit.toBaseRate
}
