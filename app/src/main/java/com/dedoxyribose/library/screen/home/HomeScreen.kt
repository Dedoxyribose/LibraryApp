package com.dedoxyribose.library.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.dedoxyribose.library.R
import com.dedoxyribose.library.model.News
import com.dedoxyribose.library.views.NewsItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    onMoveToDetails: (Long) -> Unit,
    title: MutableState<String>
) {
    title.value = stringResource(id = R.string.home_title)

    val lazyItems: LazyPagingItems<News> = viewModel.newsFlow.collectAsLazyPagingItems()

    if (lazyItems.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(lazyItems) { news ->
                news?.let {
                    NewsItem(it, true, onMoveToDetails)
                }
            }
            if (lazyItems.loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
        if (lazyItems.loadState.append is LoadState.Error ||
            lazyItems.loadState.refresh is LoadState.Error
        ) {
            val errorText = stringResource(id = R.string.fail_load_error)
            val retryText = stringResource(id = R.string.retry)
            LaunchedEffect(Unit) {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = errorText,
                    actionLabel = retryText,
                    duration = SnackbarDuration.Indefinite
                )
                if (snackBarResult == SnackbarResult.ActionPerformed) {
                    lazyItems.retry()
                }
            }
        }
    }
}
