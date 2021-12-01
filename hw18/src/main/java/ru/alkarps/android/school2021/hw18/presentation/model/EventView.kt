package ru.alkarps.android.school2021.hw18.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class EventView(
    val name: String,
    val date: String,
    val startTime: String? = null,
    val location: String? = null,
    val uuid: String = UUID.randomUUID().toString()
) : Parcelable
