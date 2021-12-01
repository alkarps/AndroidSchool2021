package ru.alkarps.android.school2021.hw18.data.language

import ru.alkarps.android.school2021.hw18.data.di.DataScope
import ru.alkarps.android.school2021.hw18.data.language.api.LanguageApi
import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient
import ru.alkarps.android.school2021.hw18.domen.model.Language
import javax.inject.Inject

/**
 * Реализация [LanguageClient]
 *
 * @property api клиент для вызова HolidayApi для получения доступных языков
 * @property converter конвертер модели представления дата слоя в модель представления бизнес слоя
 */
@DataScope
class ImplLanguageClient @Inject constructor(
    private val api: LanguageApi,
    private val converter: LanguageConverter
) : LanguageClient {
    override fun getLanguages(): List<Language> = api.getLanguages().let { converter.fromDto(it) }
}