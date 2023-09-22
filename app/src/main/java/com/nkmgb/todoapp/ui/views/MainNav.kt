package com.nkmgb.todoapp.ui.views

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainGraph(drawerState: DrawerState) {
    navigation(startDestination = MainNavOption.HomeScreen.name, route = NavRoutes.MainRoute.name) {
        composable(MainNavOption.HomeScreen.name){
            AppMainScreen()
//            HomeScreen(drawerState)
        }
        composable(MainNavOption.SettingsScreen.name){
            AppMainScreen()
//            todoListHomePage(openDrawer = openDrawer)
        }
        composable(MainNavOption.AboutScreen.name){
            AppMainScreen()
        }
    }
}

enum class MainNavOption {
    HomeScreen,
    AboutScreen,
    SettingsScreen,
}