package ru.alkarps.android.school2021.hw18

import android.app.Application
import ru.alkarps.android.school2021.hw18.data.di.DaggerDataComponent
import ru.alkarps.android.school2021.hw18.data.di.DataContextModule
import ru.alkarps.android.school2021.hw18.domen.di.DaggerDomenComponent
import ru.alkarps.android.school2021.hw18.presentation.di.DaggerHolidayMainComponent
import ru.alkarps.android.school2021.hw18.presentation.di.HolidayMainComponent

/**
 * Приложение клиента к HolidayApi
 *
 * @constructor Создает новый экземпляр приложения
 */
class HolidayApiApplication : Application() {
    val holidayMain: HolidayMainComponent = DaggerHolidayMainComponent.builder()
        .domenComponent(
            DaggerDomenComponent.builder()
                .dataComponent(
                    DaggerDataComponent.builder()
                        .dataContextModule(DataContextModule(this))
                        .build()
                ).build()
        ).build()
}