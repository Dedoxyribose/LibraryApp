package com.dedoxyribose.library.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dedoxyribose.library.R

@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    title: MutableState<String>
) {
    title.value = stringResource(id = R.string.search_title)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Экран поиска")
    }
}