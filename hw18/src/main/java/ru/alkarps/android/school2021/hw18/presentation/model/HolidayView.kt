package ru.alkarps.android.school2021.hw18.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель для отображения праздника
 *
 * @property uuid id праздника
 * @property name название праздника
 * @property date дата праздника
 * @property observed дата отмечания праздника
 * @property public признак государственного праздника
 * @constructor Создает новый объект праздника
 */
@Parcelize
data class HolidayView(
    val uuid: String,
    val name: String,
    val date: String,
    val observed: String,
    val public: Boolean
): Parcelable
