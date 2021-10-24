package ru.alkarps.android.school2021.hw18.data.holiday.model

import kotlinx.serialization.Serializable

/**
 * Праздник
 *
 * @property name название
 * @property date дата
 * @property observed когда празднуют
 * @property public признак государственного праздника
 * @property country код страны (ISO 3166-1 alpha-2) или штата/региона/провинции (ISO 3166-2)
 * @property uuid id
 * @property weekday информация о днях недели
 * @constructor Создает новый праздник
 *
 * @see [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)
 * @see [ISO 3166-2](https://en.wikipedia.org/wiki/ISO_3166-2)
 */
@Serializable
data class HolidayDTO(
    val name: String,
    val date: String,
    val observed: String,
    val public: Boolean,
    val country: String,
    val uuid: String,
    val weekday: WeekdayDTO
)
