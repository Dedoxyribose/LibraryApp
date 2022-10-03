package com.dedoxyribose.library.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedoxyribose.library.domain.home.IHomeInteractor
import com.dedoxyribose.library.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val interactor: IHomeInteractor
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState(isLoading = true, data = emptyList()))
        private set

    init {
        load()
    }

    private fun load() {
        uiState = uiState.copy(isLoading = true, data = emptyList())
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = false, data = interactor.getNews())
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean,
    val data: List<News>
)
