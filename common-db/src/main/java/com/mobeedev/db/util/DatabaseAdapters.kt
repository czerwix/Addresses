package com.mobeedev.db.util

import com.squareup.sqldelight.ColumnAdapter
import org.threeten.bp.ZonedDateTime

val zonedDateTimeAdapter = object : ColumnAdapter<ZonedDateTime, String> {
    override fun encode(value: ZonedDateTime) = value.toString()
    override fun decode(databaseValue: String) = ZonedDateTime.parse(databaseValue)
}