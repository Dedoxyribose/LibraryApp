package com.dedoxyribose.library.domain.books.details

import com.dedoxyribose.library.model.Book

interface IBookDetailsInteractor {
    suspend fun getBook(id: Long): Book
}
