package com.nkmgb.todoapp.ui.views

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.theme.appThemeColor
import com.nkmgb.todoapp.utils.AppScreens
import com.nkmgb.todoapp.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun todoListHomePage(
    mainActivityViewModel: MainActivityViewModel? = null,
    openDrawer: () -> Unit,
    addButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = appThemeColor.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        openDrawer.invoke()

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Navigation icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Add New"
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "No employees onboarded yet.",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center
                )
            }
        },
        bottomBar = {
            Button(
                onClick = { addButtonClicked.invoke() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(R.color.black))
            ) {
                Text(mainActivityViewModel?.getButtonString() ?: "Test")
            }
        })
}


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

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainCompose(
//    navController: NavHostController = rememberNavController(),
//    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
//    vm: MainActivityViewModel
//) {
//    AppDrawerExampleTheme {
//        Surface {
//            ModalNavigationDrawer(
//                drawerState = drawerState,
//                drawerContent = {
//                    AppDrawerContent(
//                        drawerState = drawerState,
//                        menuItems = DrawerParams.drawerButtons,
//                        defaultPick = MainNavOption.HomeScreen
//                    ) { onUserPickedOption ->
//                        when (onUserPickedOption) {
//                            MainNavOption.HomeScreen -> {
//                                navController.navigate(onUserPickedOption.name) {
//                                    popUpTo(NavRoutes.MainRoute.name)
//                                }
//                            }
//
//                            MainNavOption.SettingsScreen -> {
//                                navController.navigate(onUserPickedOption.name) {
//                                    popUpTo(NavRoutes.MainRoute.name)
//                                }
//                            }
//
//                            MainNavOption.AboutScreen -> {
//                                navController.navigate(onUserPickedOption.name) {
//                                    popUpTo(NavRoutes.MainRoute.name)
//                                }
//                            }
//                        }
//                    }
//                }
//            ) {
////                val isOnboarded = emptyList<>()
//                NavHost(
//                    navController,
//                    startDestination = NavRoutes.MainRoute.name
//                ) {
////                    introGraph(navController)
//                    mainGraph(drawerState)
//                }
//            }
//        }
//    }
//}

enum class NavRoutes {
    IntroRoute,
    MainRoute,
}

//object DrawerParams {
//    val drawerButtons = arrayListOf(
//        AppDrawerItemInfo(
//            MainNavOption.HomeScreen,
//            R.string.drawer_home,
//            R.drawable.ic_launcher_foreground,
//            R.string.drawer_home
//        ),
//        AppDrawerItemInfo(
//            MainNavOption.SettingsScreen,
//            R.string.drawer_settings,
//            R.drawable.ic_launcher_foreground,
//            R.string.drawer_settings
//        ),
//        AppDrawerItemInfo(
//            MainNavOption.AboutScreen,
//            R.string.drawer_about,
//            R.drawable.ic_launcher_foreground,
//            R.string.drawer_about
//        )
//    )
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun MainActivityPreview() {
//    MainCompose(vm = ma)
//}
