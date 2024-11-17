package com.example.newsapp.util

import android.annotation.SuppressLint
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@SuppressLint("NewApi")
fun dateFormatter(inputDateTime: String?) :String {
    val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val outputFormatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.LONG)
        .withLocale(Locale.getDefault())
    val dateString = try {
        val dateTime = OffsetDateTime.parse(inputDateTime , inputFormatter)
        dateTime.format(outputFormatter)
    } catch (exception : Exception) {
        ""
    }
    return dateString
}