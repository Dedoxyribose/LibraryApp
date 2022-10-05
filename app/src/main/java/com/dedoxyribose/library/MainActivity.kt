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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dedoxyribose.library.home.HomeScreen
import com.dedoxyribose.library.navigation.BottomNavigationItem
import com.dedoxyribose.library.ui.theme.LibraryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryTheme {
                // A surface container using the 'background' color from the theme
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
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(navController) },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController, scaffoldState = scaffoldState)
            }
        },
        backgroundColor = MaterialTheme.colors.surface // Set background color to avoid the white flashing when you switch between screens
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
fun BottomNavigationBar(navController: NavController) {
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
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.titleRes)
                    )
                },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
                alwaysShowLabel = false,
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(navController, startDestination = BottomNavigationItem.HOME.route) {
        composable(BottomNavigationItem.HOME.route) {
            HomeScreen(scaffoldState = scaffoldState)
        }
        composable(BottomNavigationItem.SEARCH.route) {
            HomeScreen(scaffoldState = scaffoldState)
        }
        composable(BottomNavigationItem.MY_BOOKS.route) {
            HomeScreen(scaffoldState = scaffoldState)
        }
        composable(BottomNavigationItem.MORE.route) {
            HomeScreen(scaffoldState = scaffoldState)
        }
    }
}
