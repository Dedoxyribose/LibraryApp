package com.dedoxyribose.library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dedoxyribose.library.navigation.BottomNavigationItem
import com.dedoxyribose.library.navigation.Navigation
import com.dedoxyribose.library.navigation.core.NestedBottomNavController
import com.dedoxyribose.library.navigation.core.nestedBottomNavigator
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
    val title = remember { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(title) },
        bottomBar = { BottomNavigationBar(nestedBottomNavController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    nestedBottomNavController = nestedBottomNavController,
                    scaffoldState = scaffoldState,
                    title = title
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
fun TopBar(title: MutableState<String>) {

    TopAppBar(
        modifier = Modifier.height(70.dp),
        elevation = 0.dp,
        title = {
            Image(
                modifier = Modifier.size(width = 48.dp, height = 48.dp),
                painter = painterResource(id = R.drawable.ic_logo_splash),
                contentDescription = null
            )
            Text(
                text = title.value,
                style = MaterialTheme.typography.h1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun BottomNavigationBar(nestedBottomNavController: NestedBottomNavController) {
    val items = listOf(
        BottomNavigationItem.HOME_TAB,
        BottomNavigationItem.SEARCH_TAB,
        BottomNavigationItem.MY_BOOKS_TAB,
        BottomNavigationItem.MORE_TAB,
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
                        contentDescription = null
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
