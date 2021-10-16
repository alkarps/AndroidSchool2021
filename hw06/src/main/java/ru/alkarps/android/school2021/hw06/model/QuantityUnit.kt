package ru.alkarps.android.school2021.hw06.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuantityUnit(@StringRes val label: Int, val toBaseRate: Double) : Parcelable {
    @IgnoredOnParcel
    val fromBaseRate = 1.0 / toBaseRate
}