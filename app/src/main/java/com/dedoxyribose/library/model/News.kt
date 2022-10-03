package com.dedoxyribose.library.model

import org.joda.time.DateTime

data class News(
    val id: Long,
    val title: String,
    val subtitle: String?,
    val text: String,
    val date: DateTime,
    val views: Int
)
