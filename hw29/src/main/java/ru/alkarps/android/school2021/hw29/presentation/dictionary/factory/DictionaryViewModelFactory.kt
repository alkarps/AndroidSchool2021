package ru.alkarps.android.school2021.hw29.presentation.dictionary.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.alkarps.android.school2021.hw29.data.repositories.DictionaryRepositoryImpl
import ru.alkarps.android.school2021.hw29.domen.converter.DictionaryConverterImpl
import ru.alkarps.android.school2021.hw29.domen.interactors.DictionaryInteractorImpl
import ru.alkarps.android.school2021.hw29.presentation.dictionary.DictionaryViewModel
import ru.alkarps.android.school2021.hw29.presentation.providers.SchedulersProviderImpl

class DictionaryViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = DictionaryRepositoryImpl(context.contentResolver)
        val iteractor = DictionaryInteractorImpl(repository, DictionaryConverterImpl())
        return DictionaryViewModel(iteractor, SchedulersProviderImpl()) as T
    }
}