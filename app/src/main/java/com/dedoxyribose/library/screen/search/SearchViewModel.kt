package com.dedoxyribose.library.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dedoxyribose.library.domain.books.search.IBooksSearchInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: IBooksSearchInteractor
) : ViewModel() {

    val booksFlow = Pager(PagingConfig(pageSize = 2, initialLoadSize = 2)) {
        interactor.createBookSource()
    }.flow
        .cachedIn(viewModelScope)

}
