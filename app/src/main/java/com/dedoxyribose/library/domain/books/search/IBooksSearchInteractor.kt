package com.dedoxyribose.library.domain.books.search

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.Book

interface IBooksSearchInteractor {
    fun createBookSource(): PagingSource<Int, Book>
}
