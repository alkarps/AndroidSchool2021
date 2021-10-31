package ru.alkarps.android.school2021.hw18.data.di

import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Модуль продайдинга внешних зависимостей
 *
 * @constructor Инстанс модуля
 */
@Module
object DataExternalDependenciesModule {
    @Provides
    @DataScope
    @JvmStatic
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @DataScope
    @JvmStatic
    fun provideJson() = Json { ignoreUnknownKeys = true }
}