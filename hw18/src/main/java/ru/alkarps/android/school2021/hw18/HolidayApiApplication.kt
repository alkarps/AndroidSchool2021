package ru.alkarps.android.school2021.hw18

import android.app.Application
import android.content.Context
import ru.alkarps.android.school2021.hw18.data.di.DaggerDataComponent
import ru.alkarps.android.school2021.hw18.data.di.DataContextModule
import ru.alkarps.android.school2021.hw18.domen.di.DaggerDomenComponent
import ru.alkarps.android.school2021.hw18.domen.di.DomenComponent
import ru.alkarps.android.school2021.hw18.presentation.di.holiday.HolidayMainComponent

/**
 * Приложение клиента к HolidayApi
 *
 * @constructor Создает новый экземпляр приложения
 */
class HolidayApiApplication : Application() {
    private var domenComponent: DomenComponent? = null
    private var holidayMain: HolidayMainComponent? = null

    fun holidayMain(context: Context): HolidayMainComponent {
        if (holidayMain == null) {
            holidayMain = domenComponent(context.applicationContext).holidayMainFactory().create()
        }
        return holidayMain!!
    }

    private fun domenComponent(context: Context): DomenComponent {
        if (domenComponent == null) {
            domenComponent = DaggerDomenComponent.builder().dataComponent(
                DaggerDataComponent.builder()
                    .dataContextModule(DataContextModule(context.applicationContext))
                    .build()
            ).build()
        }
        return domenComponent!!
    }
}