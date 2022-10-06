package com.dedoxyribose.library.navigation

import androidx.annotation.StringRes
import com.dedoxyribose.library.R
import com.dedoxyribose.library.navigation.core.IBottomNavigationItem

enum class BottomNavigationItem(
    override var route: String,
    var icon: Int,
    @StringRes var titleRes: Int
) : IBottomNavigationItem {
    HOME("home_tab", R.drawable.ic_home, R.string.home_title),
    SEARCH("search_tab", R.drawable.ic_search, R.string.search_title),
    MY_BOOKS("my_books_tab", R.drawable.ic_my_books, R.string.my_books_title),
    MORE("more_tab", R.drawable.ic_more, R.string.more_title)
}
