package com.dedoxyribose.library.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dedoxyribose.library.R
import com.dedoxyribose.library.model.Book
import com.dedoxyribose.library.ui.theme.LowContentAlpha
import com.dedoxyribose.library.ui.theme.extendedColors
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BookItem(
    book: Book,
    divider: Boolean,
    onClick: ((Long) -> Unit)?
) {
    val padding = 24.dp
    val smallPadding = 8.dp
    Column(modifier = Modifier
        .padding(start = padding, top = smallPadding, end = padding)
        .clickable { onClick?.invoke(book.id) }) {
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
        if (divider) {
            Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
        }
    }
}
