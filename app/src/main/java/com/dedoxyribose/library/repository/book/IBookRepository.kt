package com.dedoxyribose.library.repository.book

import androidx.paging.PagingSource
import com.dedoxyribose.library.model.Book

interface IBookRepository {
    suspend fun getBooks(offset: Int, take: Int): List<Book>
    fun createBookDataSource(): PagingSource<Int, Book>
    suspend fun getBook(id: Long): Book
}
