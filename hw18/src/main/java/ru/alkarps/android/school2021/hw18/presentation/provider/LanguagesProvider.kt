package ru.alkarps.android.school2021.hw18.presentation.provider

import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Провайдер доступных языков
 */
interface LanguagesProvider {
    /**
     * Метод получения доступных языков
     *
     * @return список доступных языков
     */
    fun getLanguages(): Single<List<Language>>
}