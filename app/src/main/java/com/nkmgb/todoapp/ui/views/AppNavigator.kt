package com.nkmgb.todoapp.ui.views

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.nkmgb.todoapp.utils.AppScreens
import com.nkmgb.todoapp.viewmodel.MainActivityViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppRouter(
    navController: NavHostController,
    mainActivityViewModel: MainActivityViewModel?,
    openDrawer: () -> Unit,
    addButtonClicked: () -> Unit
) {
    AnimatedNavHost(navController, startDestination = AppScreens.HomeScreen.route) {
//        composable(route = AppScreens.AddTodoScreen.route + "/{empId}/{isEdit}",
//            arguments = listOf(
//                navArgument("empId") {
//                    type = NavType.StringType
//                    defaultValue = ""
//                },
//                navArgument("isEdit") {
//                    type = NavType.BoolType
//                    defaultValue = false
//                }
//            ), enterTransition = {
//                // Let's make for a really long fade in
//                slideInVertically(
//                    initialOffsetY = { 1800 }
//                )
//            }, popExitTransition = {
//                slideOutVertically(
//                    targetOffsetY = { 1800 }
//                )
//            }
//        ) {
//            val isEdit = it.arguments?.getBoolean("isEdit")
//            val empId = it.arguments?.getString("empId")
//            AddTodoScreen()
////            todoListHomePage(mainActivityViewModel, openDrawer, addButtonClicked)
////            AddEditEmployeeScreen(navController, homeViewModel, empId, isEdit!!)
//        }
        composable(route = AppScreens.EmployeeDetailScreen.route + "/{empId}",
            arguments = listOf(
                navArgument("empId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )) {
            val empId = it.arguments?.getString("empId")
            todoListHomePage(mainActivityViewModel, openDrawer, addButtonClicked)
        }
        composable(route = AppScreens.HomeScreen.route) {
            todoListHomePage(mainActivityViewModel, openDrawer, addButtonClicked)
        }
        composable(route = AppScreens.TodoList.route) {
            HomeScreen(openDrawer)
        }
        composable(route = AppScreens.Settings.route) {
            AddTodoScreen(navController)
        }
        composable(route = AppScreens.AddTodoScreen.route) {
            AddTodoScreen(navController)
        }
    }

}

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    val visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f,
            animationSpec = tween(500, 500)
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        content()
    }
}
