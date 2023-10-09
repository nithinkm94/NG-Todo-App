package com.nkmgb.todoapp.ui.views.todoadd

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.theme.appThemeColor
import com.nkmgb.todoapp.ui.views.todoshow.TodoListContainer
import com.nkmgb.todoapp.ui.widgets.BottomBar
import com.nkmgb.todoapp.viewmodel.MainActivityViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListHomePage(
    viewModel: MainActivityViewModel? = null,
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
                    IconButton(onClick = {
                        addButtonClicked.invoke()
                    }) {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Add New"
                        )
                    }
                }
            )
        },
        content = {
            TodoListContainer(it)
        },
        bottomBar = {
            BottomBar(
                viewModel?.getButtonString().toString(),
                addButtonClicked
            )
        })
}
