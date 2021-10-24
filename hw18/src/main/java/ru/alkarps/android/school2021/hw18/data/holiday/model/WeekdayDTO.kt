package ru.alkarps.android.school2021.hw18.data.holiday.model

import kotlinx.serialization.Serializable

/**
 * Информация о днях недели праздника
 *
 * @property date дата праздника
 * @property observed дата официального празднования
 * @constructor Новый объект
 */
@Serializable
data class WeekdayDTO(
    val date: DateInfoDTO,
    val observed: DateInfoDTO
)
