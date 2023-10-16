package com.nkmgb.todoapp.ui.views.todoshow

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.room.database.jointable.JoinTodoItemTodoLabel
import com.nkmgb.todoapp.ui.content.state.TodoScreenState
import com.nkmgb.todoapp.ui.viewmodel.TodoViewModel
import com.nkmgb.todoapp.utils.observeLifecycle
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TodoListStickyContainer(
    paddingValues: PaddingValues,
    todoViewModel: TodoViewModel = hiltViewModel()
) {
    val state by todoViewModel.state.collectAsState(initial = TodoScreenState.None)

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
                    TodoListEmptyView()
                } else {
                    //
                    LazyColumnClickableAdvDemo(data, {})
                }
            }

            else -> Unit
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumnClickableAdvDemo(
    todoItems: List<JoinTodoItemTodoLabel>,
    selectedItem: (JoinTodoItemTodoLabel) -> Unit
) {
    val grouped = todoItems.groupBy { it.todoItem.dueDate }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        grouped.forEach { (section, todoItems) ->
            stickyHeader {
                Text(
                    text = getSectionTitle(section),
                    color = Color.White,
                    modifier = Modifier
                        .background(color = Color.Black)
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

            items(
                items = todoItems,
                itemContent = {
                    TodoListItem(
                        todoItem = it,
                        selectedItem = selectedItem
                    )
                }
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun getSectionTitle(section: String): String {
    val sdf = SimpleDateFormat("yyyy/MM/dd")
    val currentDate = sdf.format(Date())
    return when {
        section.isNullOrEmpty() -> "No Due Task"
        section == currentDate -> "Today"
        else -> {
            val inputFormat = SimpleDateFormat("yyyy/MM/dd")
            val outputFormat = SimpleDateFormat("dd/MM/YYYY")
            val date: Date = inputFormat.parse(section)
            val outputDateStr: String = outputFormat.format(date)
//            var formatter = SimpleDateFormat("dd/MM/yyyy")
//            section.format(formatter)
            outputDateStr
        }
    }
}

@Composable
fun TodoListItem(todoItem: JoinTodoItemTodoLabel, selectedItem: (JoinTodoItemTodoLabel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { selectedItem(todoItem) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            todoItem.todoLabel.name,
            fontSize = 20.sp,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(10.dp),
            textAlign = TextAlign.Start,
            color = colorResource(id = todoItem.todoLabel.color)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
                .fillMaxHeight()
                .background(
                    color = Color.Transparent,
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
                    todoItem.todoItem.title,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    textAlign = TextAlign.Start
                )

                Text(
                    todoItem.todoItem.description,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    textAlign = TextAlign.Start
                )
            }

            IconButton(
                onClick = { },
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
