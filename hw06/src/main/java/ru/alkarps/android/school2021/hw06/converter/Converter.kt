package ru.alkarps.android.school2021.hw06.converter

import ru.alkarps.android.school2021.hw06.model.ConverterValue
import ru.alkarps.android.school2021.hw06.model.QuantityUnit
import java.math.RoundingMode

class Converter {
    fun convert(old: List<ConverterValue>): List<ConverterValue> {
        var new = old.toMutableList()
        val base = new.removeFirst()
        val baseValue = base.getBaseValue()
        new = new.map { it.copy(value = around(baseValue * it.unit.fromBaseRate)) }
            .sortedBy { it.value }.toMutableList()
        new.add(0, base)
        return new
    }

    fun startList(units: List<QuantityUnit>) = convert(units.map { ConverterValue(it, 100.0) })

    private fun around(value: Double) =
        value.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
}