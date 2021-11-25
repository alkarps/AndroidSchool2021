package ru.alkarps.android.school2021.hw18.util

import ru.alkarps.android.school2021.hw18.domen.model.Period
import java.text.SimpleDateFormat
import java.util.*

/**
 * Метод конвертации [Calendar] в [Period]
 *
 * @return дата в формате [Period]
 */
fun Calendar.toPeriod(): Period =
    Period(get(Calendar.YEAR), get(Calendar.MONTH) + 1, get(Calendar.DAY_OF_MONTH))

/**
 * Метод конвертации [Calendar] в [String] в формате yyyy.MM.dd.
 *
 * @return Дата в формате yyyy.MM.dd
 */
fun Calendar.asString(): String =
    SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(time)

/**
 * Метод конвертации [String] в формате yyyy.MM.dd в [Calendar].
 *
 * @return Дата в формате yyyy.MM.dd
 * @exception [java.text.ParseException] если строка не в формате yyyy.MM.dd
 */
fun String.toCalendar(): Calendar = Calendar.getInstance()
    .also { it.time = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).parse(this) }

