package com.dedoxyribose.library.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dedoxyribose.library.model.News

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
    Column(modifier = Modifier.padding(start = padding, top = padding, end = padding)) {
        Text(
            text = news.title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary
        )
        if (news.subtitle != null) {
            Text(
                text = news.subtitle,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSurface
            )
        }
        Text(
            text = news.text,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.size(padding))
        Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
    }
}
