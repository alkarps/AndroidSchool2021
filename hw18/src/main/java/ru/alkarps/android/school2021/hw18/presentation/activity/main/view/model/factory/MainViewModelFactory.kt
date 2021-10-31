package ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.alkarps.android.school2021.hw18.data.di.DaggerDataComponent
import ru.alkarps.android.school2021.hw18.data.di.DataExternalDependenciesModule
import ru.alkarps.android.school2021.hw18.data.holiday.ImplHolidayClient
import ru.alkarps.android.school2021.hw18.data.holiday.api.impl.ImplHolidayApi
import ru.alkarps.android.school2021.hw18.domen.holiday.impl.ImplHolidayService
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel
import ru.alkarps.android.school2021.hw18.presentation.provider.impl.ImplHolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.impl.ImplSchedulersProvider

/**
 * Фабрика [MainViewModel]
 *
 * @constructor Новый инстанс [ViewModelProvider.Factory]
 */
class MainViewModelFactory : ViewModelProvider.Factory {
    /**
     * Метод создания [MainViewModel]
     *
     * @param T [ViewModel]
     * @param modelClass класс [ViewModel]
     * @return экземпляр [MainViewModel]
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        val json = Json { ignoreUnknownKeys = true }
        val dataDependenciesModule = DataExternalDependenciesModule(okHttpClient, json)
        val holidayClient = DaggerDataComponent.builder()
            .dataExternalDependenciesModule(dataDependenciesModule)
            .build()
            .implHolidayClient()
        val holidayService = ImplHolidayService(holidayClient)
        val presentationHolidayConverter =
            ru.alkarps.android.school2021.hw18.presentation.provider.converter.impl.ImplHolidayConverter()
        val holidaysProvider = ImplHolidaysProvider(holidayService, presentationHolidayConverter)
        val schedulersProvider = ImplSchedulersProvider()
        return MainViewModel(schedulersProvider, holidaysProvider) as T
    }
}