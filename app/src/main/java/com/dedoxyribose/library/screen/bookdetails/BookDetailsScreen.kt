package com.dedoxyribose.library.screen.bookdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dedoxyribose.library.R
import com.dedoxyribose.library.ui.theme.extendedColors
import com.dedoxyribose.library.views.BookItem

@Composable
fun BookDetailsScreen(
    viewModel: BookDetailsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    title: MutableState<String>
) {
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
    if (uiState.book != null) {
        LaunchedEffect(Unit) {
            title.value = uiState.book.title
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            BookItem(book = uiState.book, onClick = null, divider = false)
            Text(
                text = uiState.book.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.extendedColors.primaryText,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}
