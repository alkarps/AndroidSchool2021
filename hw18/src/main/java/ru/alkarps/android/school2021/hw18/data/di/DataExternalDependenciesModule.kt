package ru.alkarps.android.school2021.hw18.data.di

import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

@Module
class DataExternalDependenciesModule(
    private val okHttpClient: OkHttpClient,
    private val jsonSerializer: Json
) {
    @Provides
    fun provideOkHttpClient() = okHttpClient

    @Provides
    fun provideJson() = jsonSerializer
}