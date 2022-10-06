package com.dedoxyribose.library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.dedoxyribose.library.navigation.BottomNavigationItem
import com.dedoxyribose.library.navigation.core.NestedBottomNavController
import com.dedoxyribose.library.navigation.core.backPressHandlingComposable
import com.dedoxyribose.library.navigation.core.nestedBottomNavigator
import com.dedoxyribose.library.screen.home.HomeScreen
import com.dedoxyribose.library.screen.newsdetails.NewsDetailsScreen
import com.dedoxyribose.library.ui.theme.LibraryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val nestedBottomNavController = nestedBottomNavigator(
        BottomNavigationItem.values().asList()
    )

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(nestedBottomNavController.navController) },
        bottomBar = { BottomNavigationBar(nestedBottomNavController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    nestedBottomNavController = nestedBottomNavController,
                    scaffoldState = scaffoldState,
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun TopBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = BottomNavigationItem.values().firstOrNull {
        it.route == navBackStackEntry?.destination?.route
    }
    TopAppBar(
        modifier = Modifier.height(70.dp),
        title = {
            Image(
                modifier = Modifier.size(width = 48.dp, height = 48.dp),
                painter = painterResource(id = R.drawable.ic_logo_splash),
                contentDescription = null
            )
            Text(
                text = currentScreen?.titleRes?.let { stringResource(id = it) }.orEmpty(),
                style = MaterialTheme.typography.h1
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun BottomNavigationBar(nestedBottomNavController: NestedBottomNavController) {
    val items = listOf(
        BottomNavigationItem.HOME,
        BottomNavigationItem.SEARCH,
        BottomNavigationItem.MY_BOOKS,
        BottomNavigationItem.MORE,
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        val currentRootRoute = nestedBottomNavController.currentRootRouteAsState()
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.titleRes)
                    )
                },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
                alwaysShowLabel = false,
                selected = currentRootRoute.value == item.route,
                onClick = {
                    nestedBottomNavController.navigateTo(item.route)
                }
            )
        }
    }
}

@Composable
fun Navigation(
    nestedBottomNavController: NestedBottomNavController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        nestedBottomNavController.navController,
        startDestination = BottomNavigationItem.HOME.route
    ) {
        navigation(startDestination = "home", route = BottomNavigationItem.HOME.route) {
            backPressHandlingComposable(
                "home",
                nestedBottomNavController = nestedBottomNavController
            ) {
                HomeScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController
                )
            }
            backPressHandlingComposable(
                route = "newsDetails1/{newsId}",
                arguments = listOf(navArgument("newsId") { type = NavType.LongType }),
                nestedBottomNavController = nestedBottomNavController
            ) {

                NewsDetailsScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController
                )

            }
        }


        navigation(startDestination = "home2", route = BottomNavigationItem.SEARCH.route) {
            backPressHandlingComposable(
                "home2",
                nestedBottomNavController = nestedBottomNavController
            ) {
                HomeScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController
                )
            }
            backPressHandlingComposable(
                route = "newsDetails2/{newsId}",
                arguments = listOf(navArgument("newsId") { type = NavType.LongType }),
                nestedBottomNavController = nestedBottomNavController
            ) {

                NewsDetailsScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController
                )

            }
        }
        navigation(startDestination = "home3", route = BottomNavigationItem.MY_BOOKS.route) {
            backPressHandlingComposable(
                "home3",
                nestedBottomNavController = nestedBottomNavController
            ) {
                HomeScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController
                )
            }
            backPressHandlingComposable(
                route = "newsDetails/{newsId}",
                arguments = listOf(navArgument("newsId") { type = NavType.LongType }),
                nestedBottomNavController = nestedBottomNavController
            ) {

                NewsDetailsScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController
                )

            }
        }
        navigation(startDestination = "home4", route = BottomNavigationItem.MORE.route) {
            backPressHandlingComposable(
                "home4",
                nestedBottomNavController = nestedBottomNavController
            ) {
                HomeScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController
                )
            }
            backPressHandlingComposable(
                route = "newsDetails4/{newsId}",
                arguments = listOf(navArgument("newsId") { type = NavType.LongType }),
                nestedBottomNavController = nestedBottomNavController
            ) {

                NewsDetailsScreen(
                    scaffoldState = scaffoldState,
                    navController = nestedBottomNavController.navController
                )

            }
        }
    }
}
