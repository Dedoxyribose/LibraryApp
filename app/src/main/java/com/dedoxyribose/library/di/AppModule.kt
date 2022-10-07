package com.dedoxyribose.library.di

import com.dedoxyribose.library.repository.book.BookRepository
import com.dedoxyribose.library.repository.book.IBookRepository
import com.dedoxyribose.library.repository.news.INewsRepository
import com.dedoxyribose.library.repository.news.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepo(newsRepository: NewsRepository): INewsRepository

    @Binds
    @Singleton
    abstract fun bindBookRepo(bookRepository: BookRepository): IBookRepository
}
