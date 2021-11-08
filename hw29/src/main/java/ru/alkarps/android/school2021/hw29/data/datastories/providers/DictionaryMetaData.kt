package ru.alkarps.android.school2021.hw29.data.datastories.providers

import android.net.Uri

object DictionaryMetaData {
    const val AUTHORITY = "ru.alkarps.android.school2021.hw29"
    const val TRANSLATES_DATA_TYPE = "translates"
    val AUTHORITY_URI = Uri.parse("content://$AUTHORITY")
    val TRANSLATES_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, TRANSLATES_DATA_TYPE)
    const val TRANSLATES_CONTENT_TYPE = "$AUTHORITY.$TRANSLATES_DATA_TYPE"
}