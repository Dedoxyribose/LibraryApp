package com.dedoxyribose.library.model

import androidx.annotation.StringRes
import com.dedoxyribose.library.R

enum class Genre(
    @StringRes val titleRes: Int
) {
    DRAMA(R.string.drama),
    EPIC(R.string.epic),
    ADVENTURES(R.string.adventures),
    COMEDY(R.string.comedy),
    DETECTIVE(R.string.detective),
    FANTASTIC(R.string.fantastic),
    FANTASY(R.string.fantasy),
    FAIRY_TALE(R.string.fairy_tale)
}
