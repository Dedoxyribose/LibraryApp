package com.dedoxyribose.library.navigation.core

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.*

interface IBottomNavigationItem {
    val route: String
}

data class NavigationState(
    val realBackStack: MutableState<List<String>>,
    val addToRealBackStack: MutableState<Boolean>,
    val bottomNavigationItems: List<IBottomNavigationItem>
) {
    val bottomItemRoutes: List<String> = bottomNavigationItems.map { it.route }
}

@Composable
fun nestedBottomNavigator(
    bottomNavigationItems: List<IBottomNavigationItem>
): NestedBottomNavController {
    val realBackStack = remember { mutableStateOf(listOf<String>()) }
    val addToRealBackStack = remember { mutableStateOf(true) }
    val navController = rememberNavController()
    val navigationState = NavigationState(
        realBackStack = realBackStack,
        addToRealBackStack = addToRealBackStack,
        bottomNavigationItems = bottomNavigationItems
    )

    registerNavControllerListener(
        navController = navController,
        navigationState = navigationState
    )

    return NestedBottomNavController(navController, navigationState)
}

class NestedBottomNavController(
    val navController: NavHostController,
    val navigationState: NavigationState
) {

    fun currentRootRouteAsState(): State<String?> {
        return derivedStateOf {
            navigationState.realBackStack.value.lastOrNull()
        }
    }

    fun navigateTo(route: String) {
        if (route in navigationState.bottomItemRoutes) {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}

fun registerNavControllerListener(
    navController: NavController,
    navigationState: NavigationState
) {
    val firstBottomItem = navigationState.bottomNavigationItems.first()
    navController.addOnDestinationChangedListener { _, destination, _ ->

        val currentTabRoute = when (destination.route) {
            in navigationState.bottomItemRoutes -> {
                destination.route.orEmpty()
            }
            else -> {
                findFirstNestedNavGraph(destination)?.route.orEmpty()
            }
        }

        if (navigationState.realBackStack.value.lastOrNull() == currentTabRoute) {
            navigationState.addToRealBackStack.value = true
            return@addOnDestinationChangedListener
        }

        if (navigationState.addToRealBackStack.value) {
            val updatedStack = navigationState.realBackStack.value.toMutableList()
            if (!updatedStack.contains(currentTabRoute)) {
                updatedStack.add(currentTabRoute)
            } else if (updatedStack.contains(currentTabRoute)) {
                if (currentTabRoute == firstBottomItem.route) {
                    val homeCount = Collections.frequency(
                        updatedStack,
                        firstBottomItem.route
                    )
                    if (homeCount < 2) {
                        updatedStack.add(currentTabRoute)
                    } else {
                        updatedStack.removeAt(updatedStack.lastIndexOf(currentTabRoute))
                        updatedStack.add(currentTabRoute)
                    }
                } else {
                    updatedStack.remove(currentTabRoute)
                    updatedStack.add(currentTabRoute)
                }
            }
            navigationState.realBackStack.value = updatedStack
        }
        navigationState.addToRealBackStack.value = true
    }
}

fun findFirstNestedNavGraph(
    destination: NavDestination
): NavGraph? {
    return if (destination.parent?.parent != null) {
        findFirstNestedNavGraph(destination.parent!!)
    } else {
        destination as? NavGraph
    }
}

@Composable
fun BackPressComposable(
    nestedBottomNavController: NestedBottomNavController,
    content: @Composable () -> Unit
) {
    content()
    BackPressHandler(
        navigationState = nestedBottomNavController.navigationState,
        navController = nestedBottomNavController.navController
    )
}

@Composable
fun BackPressHandler(
    navigationState: NavigationState,
    navController: NavController
) {

    val activity = (LocalContext.current as? Activity)
    BackHandler {
        val isRootRoute =
            navController.currentDestination?.route in navigationState.bottomItemRoutes
        val isStartRouteInFirstLevelGraph =
            navController.currentDestination?.parent?.startDestinationRoute ==
                    navController.currentDestination?.route &&
                    navController.currentDestination?.parent?.route in navigationState.bottomItemRoutes
        if (!isRootRoute && !isStartRouteInFirstLevelGraph) {
            navigationState.addToRealBackStack.value = false
            navController.popBackStack()
            return@BackHandler
        }

        val updatedStack = navigationState.realBackStack.value.toMutableList()
        if (updatedStack.size > 1) {
            updatedStack.removeLast()
            val destinationRoute = updatedStack.last()
            navigationState.addToRealBackStack.value = false
            navigationState.realBackStack.value = updatedStack

            navController.navigate(destinationRoute) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

        } else {
            if (updatedStack.size == 1) {
                activity?.finish()
            }
        }
    }
}

fun NavGraphBuilder.backPressHandlingComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    nestedBottomNavController: NestedBottomNavController,
    content: @Composable () -> Unit
) {
    composable(route, arguments, deepLinks) {
        BackPressComposable(nestedBottomNavController) {
            content()
        }
    }
}