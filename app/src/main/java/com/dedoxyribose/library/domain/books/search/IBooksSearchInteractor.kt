package com.dedoxyribose.library.domain.books.search

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.Book
import com.dedoxyribose.library.repository.book.BookSearchRequest

interface IBooksSearchInteractor {
    fun createBookSource(bookSearchRequest: BookSearchRequest): PagingSource<Int, Book>
}
