package com.dedoxyribose.library.di.newsdetails

import com.dedoxyribose.library.domain.newsdetails.INewsDetailsInteractor
import com.dedoxyribose.library.domain.newsdetails.NewsDetailsInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class NewsDetailsModule {
    @Binds
    @ViewModelScoped
    abstract fun bindNewsDetailsInteractor(
        interactor: NewsDetailsInteractor
    ): INewsDetailsInteractor
}
