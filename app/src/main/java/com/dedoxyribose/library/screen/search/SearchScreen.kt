package com.dedoxyribose.library.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.dedoxyribose.library.R
import com.dedoxyribose.library.model.Book
import com.dedoxyribose.library.model.Genre
import com.dedoxyribose.library.views.BookItem
import com.dedoxyribose.library.views.ChipGroup

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    onMoveToDetails: (Long) -> Unit,
    title: MutableState<String>
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        title.value = context.getString(R.string.search_title)
    }

    Column {
        SearchBar(viewModel)

        GenreBar(viewModel)

        val lazyItems: LazyPagingItems<Book> = viewModel.booksFlow.collectAsLazyPagingItems()

        if (lazyItems.loadState.refresh is LoadState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                items(lazyItems) { book ->
                    book?.let {
                        BookItem(book = book, divider = true, onClick = { onMoveToDetails(it) })
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
}

@Composable
fun SearchBar(viewModel: SearchViewModel) {
    val searchTextPadding = 16.dp
    val searchTextInnerVerticalPadding = 8.dp
    val searchTextInnerHorizontalPadding = 12.dp
    val searchText = viewModel.searchText.collectAsState("")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .padding(
                start = searchTextPadding,
                end = searchTextPadding,
                bottom = searchTextPadding
            ),
        contentAlignment = Alignment.Center,
    ) {
        BasicTextField(
            value = searchText.value,
            onValueChange = { viewModel.onSearchTextChange(it) },
            modifier = Modifier
                .background(color = MaterialTheme.colors.onPrimary)
                .padding(
                    horizontal = searchTextInnerHorizontalPadding,
                    vertical = searchTextInnerVerticalPadding
                )
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun GenreBar(viewModel: SearchViewModel) {
    val genres = viewModel.selectedGenres.collectAsState(emptySet())
    val context = LocalContext.current
    ChipGroup(
        dataList = Genre.values().asList(),
        selected = genres.value,
        dataToText = { context.getString(it.titleRes) },
        onSelectionChanged = { genre, isSelected ->
            viewModel.onGenreSelectionChange(
                genre,
                isSelected
            )
        }
    )
}
