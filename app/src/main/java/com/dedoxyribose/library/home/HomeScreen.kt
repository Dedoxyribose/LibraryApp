package com.dedoxyribose.library.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dedoxyribose.library.R
import com.dedoxyribose.library.model.News
import com.dedoxyribose.library.ui.theme.LowContentAlpha
import com.dedoxyribose.library.utils.DateFormatter

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    if (viewModel.uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(viewModel.uiState.data, key = { it.id }) {
                NewsItem(it)
            }
        }
    }
}


@Composable
fun NewsItem(news: News) {
    val padding = 24.dp
    val halfPadding = 12.dp
    Column(modifier = Modifier.padding(start = padding, top = padding, end = padding)) {
        Text(
            text = news.title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.size(halfPadding))
        if (news.subtitle != null) {
            Text(
                text = news.subtitle,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.size(halfPadding))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            CompositionLocalProvider(LocalContentAlpha provides LowContentAlpha) {
                Text(
                    text = DateFormatter.format(news.date),
                    style = MaterialTheme.typography.caption,
                )
                Spacer(modifier = Modifier.size(halfPadding))
                Image(painter = painterResource(id = R.drawable.ic_eye), contentDescription = null)
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = news.views.toString(),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
        Spacer(modifier = Modifier.size(halfPadding))
        Text(
            text = news.text,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.size(padding))
        Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
    }
}
