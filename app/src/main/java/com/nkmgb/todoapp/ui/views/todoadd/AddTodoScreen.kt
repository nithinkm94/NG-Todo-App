package com.nkmgb.todoapp.ui.views.todoadd

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.nkmgb.todoapp.ui.widgets.TodoTopAppBar
import com.nkmgb.todoapp.utils.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TodoTopAppBar(navController)
        }
    ) {
        TodoContainer(it, onBackPress = {
            navController.popBackStack(AppScreens.HomeScreen.route, false)
        })
    }
}
