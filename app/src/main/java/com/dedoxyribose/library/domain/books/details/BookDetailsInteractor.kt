package com.dedoxyribose.library.domain.books.details

import com.dedoxyribose.library.model.Book
import com.dedoxyribose.library.repository.book.IBookRepository
import javax.inject.Inject

class BookDetailsInteractor @Inject constructor(
    private val bookRepository: IBookRepository
) : IBookDetailsInteractor {
    override suspend fun getBook(id: Long): Book {
        return bookRepository.getBook(id)
    }
}
