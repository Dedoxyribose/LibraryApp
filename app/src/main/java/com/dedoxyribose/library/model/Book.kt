package com.dedoxyribose.library.model

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val coverUrl: String,
    val genres: List<Genre>,
    val isPopular: Boolean
)
