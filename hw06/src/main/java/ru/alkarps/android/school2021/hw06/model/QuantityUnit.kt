package ru.alkarps.android.school2021.hw06.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuantityUnit(@StringRes val label: Int) : Parcelable