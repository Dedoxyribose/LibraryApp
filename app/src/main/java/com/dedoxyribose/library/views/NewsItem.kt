package com.dedoxyribose.library.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dedoxyribose.library.R
import com.dedoxyribose.library.model.News
import com.dedoxyribose.library.ui.theme.LowContentAlpha
import com.dedoxyribose.library.ui.theme.extendedColors
import com.dedoxyribose.library.utils.DateFormatter


@Composable
fun NewsItem(
    news: News,
    partialView: Boolean,
    onMoveToDetails: ((Long) -> Unit)?
) {
    val padding = 24.dp
    val halfPadding = 12.dp
    val partialTextMaxLength = 500
    Column(modifier = Modifier.padding(start = padding, top = padding, end = padding)) {
        Text(
            text = news.title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.extendedColors.primaryText
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
        var isCut = false
        val text = if (partialView && news.text.length > partialTextMaxLength) {
            isCut = true
            news.text.substring(0, partialTextMaxLength) + "..."
        } else {
            news.text
        }
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface
        )
        if (isCut) {
            Text(
                text = stringResource(id = R.string.read_full_article),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.clickable {
                    onMoveToDetails?.invoke(news.id)
                }
            )
        }
        Spacer(modifier = Modifier.size(padding))
        if (partialView) {
            Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
        }
    }
}
