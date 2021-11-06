package ru.alkarps.android.school2021.hw18.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides

@Module
class DataContextModule(private val context: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @DataScope
    fun context() = context

    @Provides
    @DataScope
    fun preferences(): SharedPreferences = preferences
}