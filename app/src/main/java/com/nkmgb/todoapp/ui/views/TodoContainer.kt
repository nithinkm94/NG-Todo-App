package com.nkmgb.todoapp.ui.views

import EditTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nkmgb.todoapp.ui.content.state.AddTodoScreenState
import com.nkmgb.todoapp.ui.models.ButtonModel
import com.nkmgb.todoapp.ui.models.EditTextModel
import com.nkmgb.todoapp.ui.models.TextViewModel
import com.nkmgb.todoapp.ui.viewmodel.AddTodoViewModel
import com.nkmgb.todoapp.ui.widgets.ButtonFilled
import com.nkmgb.todoapp.ui.widgets.DatePickerDialog
import com.nkmgb.todoapp.ui.widgets.TextViewWithHeader
import com.nkmgb.todoapp.ui.widgets.TodoDatePicker
import com.nkmgb.todoapp.ui.widgets.todoTimePickerDialog

@Composable
fun TodoContainer(
    paddingValues: PaddingValues,
    addTodoViewModel: AddTodoViewModel = hiltViewModel()
) {

    var showDatePicker by remember { mutableStateOf(false) }


    val mContext = LocalContext.current
    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }
    val mTime = remember { mutableStateOf("") }

    val todoUiState by addTodoViewModel.uiState.collectAsState()

    val state by addTodoViewModel.state.collectAsState(initial = AddTodoScreenState.None)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditTextField(
            EditTextModel(
                label = "Task",
                value = todoUiState.task,
                error = todoUiState.taskErrors,
                onTextChanged = {
                    addTodoViewModel.updateTask(it)
                },
                singleLine = true
            )
        )

        EditTextField(
            EditTextModel(
                label = "Description",
                value = todoUiState.description,
                onTextChanged = {
                    addTodoViewModel.updateDescription(it)
                },
                maxLines = 3
            ),
        )

        EditTextField(EditTextModel(label = "Info", value = todoUiState.info, onTextChanged = {
            addTodoViewModel.updateInfo(it)
        }))

        var selectedIndex by remember { mutableStateOf(-1) }
        val labels = listOf("Item 1", "Item 2", "Item 3")
        LargeDropdownMenu(
            label = "Label",
            items = labels,
            selectedIndex = selectedIndex,
            onItemSelected = { index, _ ->
                selectedIndex = index
                addTodoViewModel.updateLabel(labels[index])
            },
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
        )
        val priorities = listOf("Item 1", "Item 2", "Item 3")
        LargeDropdownMenu(
            label = "Priority",
            items = priorities,
            selectedIndex = selectedIndex,
            onItemSelected = { index, _ ->
                selectedIndex = index
                addTodoViewModel.updatePriority(priorities[index])
            },
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
        )

        TextViewWithHeader(
            TextViewModel(
                header = "Date",
                hint = "Select due date",
                textValue = mDate.value.ifBlank { null },
                onClick = {
                    DatePickerDialog(mContext, onDateChange = { date ->
                        mDate.value = date
                    }).show()
                    showDatePicker = true
                }
            )
        )


        TextViewWithHeader(
            TextViewModel(
                header = "Time",
                hint = "Select due time",
                textValue = mTime.value.ifBlank { null },
                onClick = {
                    todoTimePickerDialog(mContext, onTimeChange = { time, timeIn24Hours ->
                        mTime.value = time
                    }).show()
                    showDatePicker = true
                }
            )
        )

        ButtonFilled(
            ButtonModel(label = "Save", enabled = true, onClick = {
                addTodoViewModel.onSave()
            })
        )
        //TODO
        TodoDatePicker()
        when (state) {
            is AddTodoScreenState.Loading -> CircularProgressIndicator()
            else -> Unit
        }
    }
}