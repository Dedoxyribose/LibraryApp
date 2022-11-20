package com.dedoxyribose.library.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dedoxyribose.library.ui.theme.extendedColors

@Composable
fun <T> Chip(
    data: T,
    isSelected: Boolean = false,
    dataToText: (T) -> String,
    onSelectionChanged: (T, Boolean) -> Unit,
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(100),
        color = if (isSelected) {
            MaterialTheme.colors.secondary
        } else {
            MaterialTheme.extendedColors.graySurface
        }
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = { selected ->
                    onSelectionChanged(data, selected)
                }
            )
        ) {
            Text(
                text = dataToText(data),
                style = MaterialTheme.typography.caption,
                color = if (isSelected) {
                    MaterialTheme.colors.onPrimary
                } else {
                    MaterialTheme.colors.onSurface
                },
                modifier = Modifier.padding(12.dp, 8.dp)
            )
        }
    }
}


@Composable
fun <T> ChipGroup(
    dataList: List<T>,
    selected: Set<T>,
    dataToText: (T) -> String,
    onSelectionChanged: (T, Boolean) -> Unit,
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        LazyRow {
            item {
                Spacer(modifier = Modifier.width(8.dp))
            }
            items(dataList.size) {
                Chip(
                    data = dataList[it],
                    isSelected = dataList[it] in selected,
                    dataToText = dataToText,
                    onSelectionChanged = { chipData, isSelected ->
                        onSelectionChanged(chipData, isSelected)
                    },
                )
            }
            item {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
