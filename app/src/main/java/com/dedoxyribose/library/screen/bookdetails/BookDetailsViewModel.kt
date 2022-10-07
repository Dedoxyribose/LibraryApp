package com.dedoxyribose.library.screen.bookdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedoxyribose.library.domain.books.details.IBookDetailsInteractor
import com.dedoxyribose.library.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val interactor: IBookDetailsInteractor
) : ViewModel() {

    var uiState by mutableStateOf(
        BookDetailsUiState(
            book = null,
            isLoading = false,
            isError = false
        )
    )
        private set

    private val bookId: Long = checkNotNull(savedStateHandle["bookId"])

    init {
        load()
    }

    private fun load() {
        uiState = BookDetailsUiState(isLoading = true, book = null, isError = false)
        try {
            viewModelScope.launch {
                uiState = BookDetailsUiState(
                    isLoading = false,
                    book = interactor.getBook(bookId),
                    isError = false
                )
            }
        } catch (e: Exception) {
            uiState = BookDetailsUiState(isLoading = false, book = null, isError = true)
        }
    }

    fun retry() {
        load()
    }
}

data class BookDetailsUiState(
    val book: Book?,
    val isLoading: Boolean,
    val isError: Boolean
)
