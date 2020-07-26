package com.mobeedev.commonUi.utils

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

fun ZonedDateTime.getDayMonthYearString(): String? {
    val formatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return format(formatter)
}