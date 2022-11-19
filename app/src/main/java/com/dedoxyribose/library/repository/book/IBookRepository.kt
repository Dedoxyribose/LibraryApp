package com.dedoxyribose.library.repository.book

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.Book

interface IBookRepository {
    suspend fun getBooks(
        searchRequest: BookSearchRequest? = null,
        offset: Int,
        take: Int
    ): List<Book>

    fun createBookDataSource(bookSearchRequest: BookSearchRequest): PagingSource<Int, Book>
    suspend fun getBook(id: Long): Book
}
