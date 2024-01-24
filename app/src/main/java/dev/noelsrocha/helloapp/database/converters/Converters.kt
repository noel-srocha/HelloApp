package dev.noelsrocha.helloapp.database.converters

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromDateToLong(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromLongToDate(long: Long?): Date? {
        return long?.let { Date(it) }
    }
}