package com.dedoxyribose.library.di.home

import com.dedoxyribose.library.domain.home.HomeInteractor
import com.dedoxyribose.library.domain.home.IHomeInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class HomeModule {
    @Binds
    @ViewModelScoped
    abstract fun bindHomeInteractor(homeInteractor: HomeInteractor): IHomeInteractor
}
