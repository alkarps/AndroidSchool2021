package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.domen.language.LanguageService
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.presentation.provider.LanguagesProvider

/**
 * Реализация [LanguageService]
 *
 * @constructor Новый объект реализации [LanguageService]
 */
class ImplLanguagesProvider(
    private val languageService: LanguageService
) : LanguagesProvider {
    override fun getLanguages(): Single<List<Language>> =
        Single.fromCallable { languageService.getLanguages() }
}