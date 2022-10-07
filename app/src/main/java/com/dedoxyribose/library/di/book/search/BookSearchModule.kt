package com.dedoxyribose.library.di.book.search

import com.dedoxyribose.library.domain.books.search.BookSearchInteractor
import com.dedoxyribose.library.domain.books.search.IBooksSearchInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class BookSearchModule {
    @Binds
    @ViewModelScoped
    abstract fun bindBookSearchInteractor(
        bookSearchInteractor: BookSearchInteractor
    ): IBooksSearchInteractor
}
