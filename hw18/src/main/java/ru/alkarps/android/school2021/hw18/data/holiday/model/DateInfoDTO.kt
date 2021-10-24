package ru.alkarps.android.school2021.hw18.data.holiday.model

import kotlinx.serialization.Serializable

/**
 * Описание дня недели
 *
 * @property name название
 * @property numeric номер
 * @constructor Новый объект
 */
@Serializable
data class DateInfoDTO(
    val name: String,
    val numeric: String
)
