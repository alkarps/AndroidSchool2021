package ru.alkarps.android.school2021.hw18

import android.app.Application
import android.content.Context
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
    private var holidayMain: HolidayMainComponent? = null

    fun holidayMain(context: Context): HolidayMainComponent {
        if (holidayMain == null) {
            holidayMain = DaggerHolidayMainComponent.builder().domenComponent(
                DaggerDomenComponent.builder().dataComponent(
                    DaggerDataComponent.builder()
                        .dataContextModule(DataContextModule(context))
                        .build()
                ).build()
            ).build()
        }
        return holidayMain!!
    }
}