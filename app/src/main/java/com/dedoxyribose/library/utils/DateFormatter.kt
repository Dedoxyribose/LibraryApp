package com.dedoxyribose.library.utils

import org.joda.time.DateTime

object DateFormatter {
    fun format(dateTime: DateTime) = dateTime.toString("dd MMMM yyyy HH:mm:ss")
}
