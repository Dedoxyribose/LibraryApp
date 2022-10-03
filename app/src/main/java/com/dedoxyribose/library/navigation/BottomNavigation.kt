package com.dedoxyribose.library.navigation

import com.dedoxyribose.library.R

enum class BottomNavigationItem(var route: String, var icon: Int, var title: String) {
    HOME("home", R.drawable.ic_home, "Новости библиотеки"),
    SEARCH("search", R.drawable.ic_search, "Поиск книг"),
    MY_BOOKS("mybooks", R.drawable.ic_my_books, "Книги на руках"),
    MORE("more", R.drawable.ic_more, "Ещё")
}
