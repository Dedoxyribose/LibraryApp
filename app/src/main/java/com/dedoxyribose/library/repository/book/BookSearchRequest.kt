package com.dedoxyribose.library.repository.book

import com.dedoxyribose.library.model.Genre

data class BookSearchRequest(
    val searchText: String,
    val genres: Set<Genre>
)
