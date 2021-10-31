package ru.alkarps.android.school2021.hw18.presentation.di

import dagger.Component
import ru.alkarps.android.school2021.hw18.domen.di.DomenComponent
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory.MainViewModelFactory

@Component(modules = [PresentationModule::class], dependencies = [DomenComponent::class])
interface PresentationComponent {
    fun viewModelFactory(): MainViewModelFactory
}