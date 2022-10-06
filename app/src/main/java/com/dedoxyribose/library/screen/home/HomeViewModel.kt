package com.dedoxyribose.library.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dedoxyribose.library.domain.home.IHomeInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val interactor: IHomeInteractor
) : ViewModel() {

    val newsFlow = Pager(PagingConfig(pageSize = 2, initialLoadSize = 2)) {
        interactor.createNewsSource()
    }.flow
        .cachedIn(viewModelScope)

}
