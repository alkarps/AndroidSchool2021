package ru.alkarps.android.school2021.hw18.presentation.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.domen.di.DomenComponent
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory.MainViewModelFactory

/**
 * Компонент Presentation слоя
 */
@Component(modules = [PresentationModule::class], dependencies = [DomenComponent::class])
interface PresentationComponent {
    /**
     * Метод получения фабрики для создания [ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel]
     *
     * @return
     */
    fun viewModelFactory(): MainViewModelFactory
}