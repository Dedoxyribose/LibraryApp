package com.dedoxyribose.library.navigation

import androidx.annotation.StringRes
import com.dedoxyribose.library.R

enum class BottomNavigationItem(
    var route: String,
    var icon: Int,
    @StringRes var titleRes: Int
) {
    HOME("home", R.drawable.ic_home, R.string.home_title),
    SEARCH("search", R.drawable.ic_search, R.string.search_title),
    MY_BOOKS("mybooks", R.drawable.ic_my_books, R.string.my_books_title),
    MORE("more", R.drawable.ic_more, R.string.more_title)
}
