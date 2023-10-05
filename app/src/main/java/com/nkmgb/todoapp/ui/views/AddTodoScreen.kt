package com.nkmgb.todoapp.ui.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.nkmgb.todoapp.ui.widgets.TodoTopAppBar


var empName: String = ""

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AddTodoScreen(navController: NavHostController) {

    var isEdited by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TodoTopAppBar(navController)
        }
    ) {
        TodoContainer(it)
    }
}
