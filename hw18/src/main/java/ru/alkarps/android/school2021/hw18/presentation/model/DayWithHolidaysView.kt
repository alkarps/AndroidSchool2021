package ru.alkarps.android.school2021.hw18.presentation.model

import java.util.*

/**
 * Модель отображения списка с праздниками
 *
 * @property date название даты
 * @property holidays список праздников
 * @property uuid id сущности
 * @constructor новый объект
 */
data class DayWithHolidaysView(
    val date: String,
    val holidays: List<HolidayView>,
    val uuid: String = UUID.randomUUID().toString()
)
