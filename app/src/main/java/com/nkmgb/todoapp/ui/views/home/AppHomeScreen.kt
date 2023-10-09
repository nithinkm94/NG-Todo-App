package com.nkmgb.todoapp.ui.views.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.nkmgb.todoapp.ui.navigation.AppRouter
import com.nkmgb.todoapp.ui.widgets.Drawer
import com.nkmgb.todoapp.utils.AppScreens
import com.nkmgb.todoapp.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppMainScreen(mainActivityViewModel: MainActivityViewModel? = null) {
    val navController = rememberAnimatedNavController()
    Surface(color = MaterialTheme.colorScheme.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
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
        ) {
            AppRouter(navController,
                mainActivityViewModel = mainActivityViewModel,
                openDrawer = { openDrawer() },
                addButtonClicked = {
                    println("Test")
                    navController.navigate(AppScreens.AddTodoScreen.route)
                })
        }
    }
}
