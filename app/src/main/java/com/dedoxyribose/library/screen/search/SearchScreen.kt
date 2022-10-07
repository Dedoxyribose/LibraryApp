package com.dedoxyribose.library.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.dedoxyribose.library.ui.theme.LowContentAlpha
import com.dedoxyribose.library.ui.theme.extendedColors
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    title: MutableState<String>
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        title.value = context.getString(R.string.search_title)
    }

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
            items(lazyItems) { news ->
                news?.let {
                    BookItem(book = it)
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

@Composable
fun BookItem(book: Book) {
    val padding = 24.dp
    val smallPadding = 8.dp
    Column(modifier = Modifier.padding(start = padding, top = smallPadding, end = padding)) {
        Row {
            GlideImage(
                imageModel = book.coverUrl,
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(80.dp)
                    .height(110.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.extendedColors.primaryText
                )
                Spacer(modifier = Modifier.size(smallPadding))
                Row {
                    Text(
                        text = book.author,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.weight(1f, fill = true)
                    )
                    Spacer(modifier = Modifier.size(padding))
                    CompositionLocalProvider(LocalContentAlpha provides LowContentAlpha) {
                        Text(
                            text = stringResource(id = R.string.page_count, book.pageCount),
                            style = MaterialTheme.typography.caption,
                            maxLines = 1
                        )
                    }
                }
                Spacer(modifier = Modifier.size(smallPadding))
                CompositionLocalProvider(LocalContentAlpha provides LowContentAlpha) {
                    val context = LocalContext.current
                    Text(
                        text = book.genres.joinToString { context.getString(it.titleRes) },
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(smallPadding))
        Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
    }
}
