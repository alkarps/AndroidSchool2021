package ru.alkarps.android.school2021.hw06.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
class Quantity(@StringRes val label: Int) : Parcelable