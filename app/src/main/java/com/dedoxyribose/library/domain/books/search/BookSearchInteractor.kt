package com.dedoxyribose.library.domain.books.search

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.Book
import com.dedoxyribose.library.repository.book.IBookRepository
import javax.inject.Inject

class BookSearchInteractor @Inject constructor(
    private val bookRepository: IBookRepository
) : IBooksSearchInteractor {
    override fun createBookSource(): PagingSource<Int, Book> {
        return bookRepository.createBookDataSource()
    }
}
