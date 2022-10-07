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

enum class Screen(
    var route: String,
    var arguments: List<NamedNavArgument> = listOf()
) {
    HOME("home"),
    SEARCH("search"),
    MY_BOOKS("myBooks"),
    MORE("more"),

    NEWS_DETAILS("newsDetails/{newsId}", listOf(navArgument("newsId") { type = NavType.LongType }))
}


@Composable
fun Navigation(
    nestedBottomNavController: NestedBottomNavController,
    scaffoldState: ScaffoldState,
    title: MutableState<String>
) {
    NavHost(
        nestedBottomNavController.navController,
        startDestination = BottomNavigationItem.HOME_TAB.route
    ) {
        navigation(
            startDestination = Screen.HOME.route,
            route = BottomNavigationItem.HOME_TAB.route
        ) {
            backPressHandlingComposable(
                Screen.HOME.route,
                nestedBottomNavController = nestedBottomNavController
            ) {
                HomeScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController,
                    title = title
                )
            }
            backPressHandlingComposable(
                route = Screen.NEWS_DETAILS.route,
                arguments = Screen.NEWS_DETAILS.arguments,
                nestedBottomNavController = nestedBottomNavController
            ) {
                NewsDetailsScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController,
                    title = title
                )
            }
        }


        navigation(
            startDestination = Screen.SEARCH.route,
            route = BottomNavigationItem.SEARCH_TAB.route
        ) {
            backPressHandlingComposable(
                Screen.SEARCH.route,
                nestedBottomNavController = nestedBottomNavController
            ) {
                SearchScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController,
                    title = title
                )
            }
        }
        navigation(
            startDestination = Screen.MY_BOOKS.route,
            route = BottomNavigationItem.MY_BOOKS_TAB.route
        ) {
            backPressHandlingComposable(
                Screen.MY_BOOKS.route,
                nestedBottomNavController = nestedBottomNavController
            ) {
                MyBooksScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController,
                    title = title
                )
            }
        }
        navigation(
            startDestination = Screen.MORE.route,
            route = BottomNavigationItem.MORE_TAB.route
        ) {
            backPressHandlingComposable(
                Screen.MORE.route,
                nestedBottomNavController = nestedBottomNavController
            ) {
                MoreScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController,
                    title = title
                )
            }
        }
    }
}
