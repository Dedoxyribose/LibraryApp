package com.dedoxyribose.library.screen.mybooks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.dedoxyribose.library.R

@Composable
fun MyBooksScreen(
    scaffoldState: ScaffoldState,
    title: MutableState<String>
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        title.value = context.getString(R.string.my_books_title)
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Экран с книгами на руках")
    }
}
