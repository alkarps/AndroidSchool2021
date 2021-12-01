package ru.alkarps.android.school2021.hw18.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Модель отображения списка с праздниками
 *
 * @property date название даты
 * @property holidays список праздников
 * @property uuid id сущности
 * @constructor новый объект
 */
@Parcelize
data class DayWithHolidaysView(
    val date: String,
    val holidays: List<HolidayView>,
    val uuid: String = UUID.randomUUID().toString()
) : Parcelable
