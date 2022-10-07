package com.dedoxyribose.library.screen.newsdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dedoxyribose.library.R
import com.dedoxyribose.library.views.NewsItem

@Composable
fun NewsDetailsScreen(
    viewModel: NewsDetailsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    navController: NavController,
    title: MutableState<String>
) {
    title.value = stringResource(id = R.string.news_details_title)

    val uiState = viewModel.uiState

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (uiState.isError) {
        val errorText = stringResource(id = R.string.fail_load_error)
        val retryText = stringResource(id = R.string.retry)
        LaunchedEffect(Unit) {
            val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = errorText,
                actionLabel = retryText,
                duration = SnackbarDuration.Indefinite
            )
            if (snackBarResult == SnackbarResult.ActionPerformed) {
                viewModel.retry()
            }
        }
    }
    if (uiState.news != null) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            NewsItem(news = uiState.news, partialView = false, navController = navController)
        }
    }
}
