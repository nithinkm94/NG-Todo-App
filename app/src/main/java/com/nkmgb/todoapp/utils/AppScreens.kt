package com.nkmgb.todoapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(val title: String, val route: String, var icon: ImageVector) {
    object HomeScreen : AppScreens("Home", "homeScreen", Icons.Default.Home)
    object AddTodoScreen :
        AppScreens("Add/Edit Todo", "addTodoItem", Icons.Default.Home)

    object EmployeeDetailScreen :
        AppScreens("Employee Details", "employeeDetailScreen", Icons.Default.Home)

    object TodoList : AppScreens("Todo List", "account", Icons.Default.List)
    object Settings : AppScreens("Settings", "contact", Icons.Default.Settings)

    fun routeWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
