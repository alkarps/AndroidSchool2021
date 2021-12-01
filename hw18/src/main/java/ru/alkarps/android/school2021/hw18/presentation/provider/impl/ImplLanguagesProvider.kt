package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.domen.language.LanguageInteractor
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.presentation.provider.LanguagesProvider
import javax.inject.Inject

/**
 * Реализация [LanguageInteractor]
 *
 * @constructor Новый объект реализации [LanguageInteractor]
 */
class ImplLanguagesProvider @Inject constructor(
    private val languageInteractor: LanguageInteractor
) : LanguagesProvider {
    override fun getLanguages(): Single<List<Language>> =
        Single.fromCallable { languageInteractor.getLanguages() }
}