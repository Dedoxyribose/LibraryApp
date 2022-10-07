package com.dedoxyribose.library.di.book.details

import com.dedoxyribose.library.domain.books.details.BookDetailsInteractor
import com.dedoxyribose.library.domain.books.details.IBookDetailsInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class BookDetailsModule {
    @Binds
    @ViewModelScoped
    abstract fun bindBookDetailsInteractor(
        interactor: BookDetailsInteractor
    ): IBookDetailsInteractor
}
