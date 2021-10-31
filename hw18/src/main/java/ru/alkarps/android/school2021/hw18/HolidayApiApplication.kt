package ru.alkarps.android.school2021.hw18

import android.app.Application
import ru.alkarps.android.school2021.hw18.data.di.DaggerDataComponent
import ru.alkarps.android.school2021.hw18.data.di.DataExternalDependenciesModule
import ru.alkarps.android.school2021.hw18.domen.di.DaggerDomenComponent
import ru.alkarps.android.school2021.hw18.presentation.di.DaggerPresentationComponent

class HolidayApiApplication : Application() {
    val presentation = DaggerPresentationComponent.builder()
        .domenComponent(
            DaggerDomenComponent.builder()
                .dataComponent(
                    DaggerDataComponent.builder()
                        .dataExternalDependenciesModule(DataExternalDependenciesModule())
                        .build()
                ).build()
        ).build()
}