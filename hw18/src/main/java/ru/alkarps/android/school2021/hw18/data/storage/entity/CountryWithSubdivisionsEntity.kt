package ru.alkarps.android.school2021.hw18.data.storage.entity

import androidx.room.Embedded
import androidx.room.Relation
import ru.alkarps.android.school2021.hw18.data.storage.HolidayApiDbContract

/**
 * Сущность для получения страны и ее ТП
 *
 * @property country описание страны
 * @property subdivisions список доступных ТП
 * @constructor Create empty Country with subdivisions entity
 */
data class CountryWithSubdivisionsEntity(
    @Embedded val country: CountryEntity,
    @Relation(
        parentColumn = HolidayApiDbContract.Country.CODE,
        entityColumn = HolidayApiDbContract.Subdivision.COUNTRY_CODE
    ) val subdivisions: List<SubdivisionEntity>
)