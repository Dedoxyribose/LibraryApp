package com.dedoxyribose.library.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.dedoxyribose.library.R
import com.dedoxyribose.library.navigation.core.IBottomNavigationItem
import com.dedoxyribose.library.navigation.core.NestedBottomNavController
import com.dedoxyribose.library.navigation.core.backPressHandlingComposable
import com.dedoxyribose.library.screen.bookdetails.BookDetailsScreen
import com.dedoxyribose.library.screen.home.HomeScreen
import com.dedoxyribose.library.screen.more.MoreScreen
import com.dedoxyribose.library.screen.mybooks.MyBooksScreen
import com.dedoxyribose.library.screen.newsdetails.NewsDetailsScreen
import com.dedoxyribose.library.screen.search.SearchScreen

enum class BottomNavigationItem(
    override var route: String,
    var icon: Int,
) : IBottomNavigationItem {
    HOME_TAB("home_tab", R.drawable.ic_home),
    SEARCH_TAB("search_tab", R.drawable.ic_search),
    MY_BOOKS_TAB("my_books_tab", R.drawable.ic_my_books),
    MORE_TAB("more_tab", R.drawable.ic_more)
}

sealed class Screen(
    var route: String,
    var arguments: List<NamedNavArgument> = listOf()
) {
    object Home : Screen("home")
    object Search : Screen("search")
    object MyBooks : Screen("myBooks")
    object More : Screen("more")

    object NewsDetails : Screen(
        "newsDetails/{newsId}",
        listOf(navArgument("newsId") { type = NavType.LongType })
    ) {
        fun createRoute(newsId: Long) = "newsDetails/$newsId"
    }

    object BookDetails : Screen(
        "bookDetails/{bookId}",
        listOf(navArgument("bookId") { type = NavType.LongType })
    ) {
        fun createRoute(bookId: Long) = "bookDetails/$bookId"
    }
}


@Composable
fun Navigation(
    nestedBottomNavController: NestedBottomNavController,
    scaffoldState: ScaffoldState,
    title: MutableState<String>
) {
    val navController = nestedBottomNavController.navController
    NavHost(
        navController,
        startDestination = BottomNavigationItem.HOME_TAB.route
    ) {
        navigation(
            startDestination = Screen.Home.route,
            route = BottomNavigationItem.HOME_TAB.route
        ) {
            backPressHandlingComposable(
                Screen.Home.route,
                nestedBottomNavController = nestedBottomNavController
            ) {
                HomeScreen(
                    scaffoldState = scaffoldState,
                    onMoveToDetails = {
                        navController.navigate(Screen.NewsDetails.createRoute(it))
                    },
                    title = title
                )
            }
            backPressHandlingComposable(
                route = Screen.NewsDetails.route,
                arguments = Screen.NewsDetails.arguments,
                nestedBottomNavController = nestedBottomNavController
            ) {
                NewsDetailsScreen(
                    scaffoldState = scaffoldState,
                    title = title
                )
            }
        }

        navigation(
            startDestination = Screen.Search.route,
            route = BottomNavigationItem.SEARCH_TAB.route
        ) {
            backPressHandlingComposable(
                Screen.Search.route,
                nestedBottomNavController = nestedBottomNavController
            ) {
                SearchScreen(
                    scaffoldState = scaffoldState,
                    onMoveToDetails = {
                        navController.navigate(Screen.BookDetails.createRoute(it))
                    },
                    title = title
                )
            }
            backPressHandlingComposable(
                route = Screen.BookDetails.route,
                arguments = Screen.BookDetails.arguments,
                nestedBottomNavController = nestedBottomNavController
            ) {
                BookDetailsScreen(
                    scaffoldState = scaffoldState,
                    title = title
                )
            }
        }
        navigation(
            startDestination = Screen.MyBooks.route,
            route = BottomNavigationItem.MY_BOOKS_TAB.route
        ) {
            backPressHandlingComposable(
                Screen.MyBooks.route,
                nestedBottomNavController = nestedBottomNavController
            ) {
                MyBooksScreen(
                    scaffoldState = scaffoldState,
                    title = title
                )
            }
        }
        navigation(
            startDestination = Screen.More.route,
            route = BottomNavigationItem.MORE_TAB.route
        ) {
            backPressHandlingComposable(
                Screen.More.route,
                nestedBottomNavController = nestedBottomNavController
            ) {
                MoreScreen(
                    scaffoldState = scaffoldState,
                    title = title
                )
            }
        }
    }
}
