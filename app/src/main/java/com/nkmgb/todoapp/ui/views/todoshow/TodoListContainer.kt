package com.nkmgb.todoapp.ui.views.todoshow

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.content.state.TodoScreenState
import com.nkmgb.todoapp.ui.theme.text16
import com.nkmgb.todoapp.ui.viewmodel.TodoViewModel
import com.nkmgb.todoapp.utils.observeLifecycle

@Composable
fun TodoListContainer(
    paddingValues: PaddingValues,
    todoViewModel: TodoViewModel = hiltViewModel()
) {
    val state by todoViewModel.state.collectAsState(initial = TodoScreenState.None)

    /*val lifecycleEvent = rememberLifecycleEvent()

    LaunchedEffect(lifecycleEvent) {
        todoViewModel.updateAction(TodoScreenAction.Load)
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            todoViewModel.getTodoList()
        }
    }*/

    todoViewModel.observeLifecycle(LocalLifecycleOwner.current.lifecycle)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (state) {
            is TodoScreenState.Loading -> CircularProgressIndicator()
            is TodoScreenState.Content -> {
                val data = (state as TodoScreenState.Content).content
                if (data.isEmpty()) {

                    AnimatedVisibility(visible = true) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Looks like you have no todos...",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = text16
                            )
                            Image(
                                painter = painterResource(id = R.drawable.empty_todo_list),
                                contentDescription = "Empty todo list",
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(items = data) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 10.dp)
                                    .fillMaxHeight()
                                    .background(
                                        color = colorResource(id = R.color.purple_transparent),
                                        shape = RoundedCornerShape(3.dp)
                                    )
                                    .clickable {

                                    }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .wrapContentWidth()
                                ) {
                                    Text(
                                        it.title,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .wrapContentHeight()
                                            .padding(10.dp),
                                        textAlign = TextAlign.Start
                                    )

                                    Text(
                                        it.description,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .wrapContentHeight()
                                            .padding(10.dp),
                                        textAlign = TextAlign.Start
                                    )
                                }

                                IconButton(
                                    onClick = { todoViewModel.delete(it) },
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_delete_24),
                                        tint = colorResource(id = R.color.purple_200),
                                        contentDescription = "Calendar"
                                    )
                                }
                            }
                        }
                    }
                }
            }

            else -> Unit
        }

    }

}
