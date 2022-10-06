package com.dedoxyribose.library.screen.newsdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedoxyribose.library.domain.newsdetails.INewsDetailsInteractor
import com.dedoxyribose.library.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val interactor: INewsDetailsInteractor
) : ViewModel() {

    var uiState by mutableStateOf(
        NewsDetailsUiState(
            news = null,
            isLoading = false,
            isError = false
        )
    )
        private set

    private val newsId: Long = checkNotNull(savedStateHandle["newsId"])

    init {
        load()
    }

    private fun load() {
        uiState = NewsDetailsUiState(isLoading = true, news = null, isError = false)
        try {
            viewModelScope.launch {
                uiState = NewsDetailsUiState(
                    isLoading = false,
                    news = interactor.getNews(newsId),
                    isError = false
                )
            }
        } catch (e: Exception) {
            uiState = NewsDetailsUiState(isLoading = false, news = null, isError = true)
        }
    }

    fun retry() {
        load()
    }
}

data class NewsDetailsUiState(
    val news: News?,
    val isLoading: Boolean,
    val isError: Boolean
)
