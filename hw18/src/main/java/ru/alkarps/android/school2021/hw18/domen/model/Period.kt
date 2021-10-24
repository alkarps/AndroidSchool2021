package ru.alkarps.android.school2021.hw18.domen.model

/**
 * Период
 *
 * @param year год
 * @param month месяц (опционально, при передачу дня - обязательное )
 * @param day день (опционально)
 * @constructor возвращает новый период поиска для получения [Holiday]
 */
data class Period(
    val year: Int,
    val month: Int? = null,
    val day: Int? = null
)
