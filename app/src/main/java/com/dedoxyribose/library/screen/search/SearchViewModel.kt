package com.dedoxyribose.library.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dedoxyribose.library.domain.books.search.IBooksSearchInteractor
import com.dedoxyribose.library.model.Genre
import com.dedoxyribose.library.repository.book.BookSearchRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: IBooksSearchInteractor
) : ViewModel() {

    private companion object {
        const val SEARCH_DEBOUNCE_MS = 300
    }

    private val searchRequest = MutableStateFlow(BookSearchRequest("", emptySet()))

    val searchText = searchRequest.map { it.searchText }

    val selectedGenres = searchRequest.map { it.genres }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val booksFlow = searchRequest
        .debounce(SEARCH_DEBOUNCE_MS.milliseconds)
        .distinctUntilChanged()
        .flatMapLatest { searchRequest ->
            Pager(PagingConfig(pageSize = 5, initialLoadSize = 6)) {
                interactor.createBookSource(searchRequest)
            }.flow
        }
        .cachedIn(viewModelScope)

    fun onSearchTextChange(searchText: String) {
        searchRequest.value = searchRequest.value.copy(searchText = searchText)
    }

    fun onGenreSelectionChange(genre: Genre, selected: Boolean) {
        searchRequest.value = searchRequest.value.copy(
            genres = searchRequest.value.genres.toMutableSet().apply {
                if (selected) {
                    add(genre)
                } else {
                    remove(genre)
                }
            }
        )
    }
}
