package com.nkmgb.todoapp.ui.views.todoadd

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.content.state.AddTodoScreenState
import com.nkmgb.todoapp.ui.mappers.Mappers.convertTodoLabelToDropDownItem
import com.nkmgb.todoapp.ui.mappers.Mappers.convertTodoPrioritiesToDropDownItem
import com.nkmgb.todoapp.ui.mappers.Mappers.convertTodoStatusToDropDownItem
import com.nkmgb.todoapp.ui.viewmodel.AddTodoViewModel
import com.nkmgb.todoapp.ui.views.labels.layout.AddLabelDialog
import com.nkmgb.todoapp.ui.views.labels.model.DialogModel
import com.nkmgb.todoapp.ui.widgets.AddIcon
import com.nkmgb.todoapp.ui.widgets.ButtonFilled
import com.nkmgb.todoapp.ui.widgets.ButtonWithOutline
import com.nkmgb.todoapp.ui.widgets.DatePickerDialog
import com.nkmgb.todoapp.ui.widgets.EditTextField
import com.nkmgb.todoapp.ui.widgets.LargeDropdownMenu
import com.nkmgb.todoapp.ui.widgets.TextViewWithHeader
import com.nkmgb.todoapp.ui.widgets.TodoDatePicker
import com.nkmgb.todoapp.ui.widgets.dropDownActionItems
import com.nkmgb.todoapp.ui.widgets.models.ButtonModel
import com.nkmgb.todoapp.ui.widgets.models.DropDownMenuModel
import com.nkmgb.todoapp.ui.widgets.models.EditTextModel
import com.nkmgb.todoapp.ui.widgets.models.TextViewModel
import com.nkmgb.todoapp.ui.widgets.todoTimePickerDialog
import com.nkmgb.todoapp.utils.observeLifecycle
import com.nkmgb.todoapp.utils.toast

@Composable
fun TodoContainer(
    paddingValues: PaddingValues,
    viewModel: AddTodoViewModel = hiltViewModel(),
    onBackPress: () -> Unit
) {
    viewModel.observeLifecycle(LocalLifecycleOwner.current.lifecycle)
    var showDatePicker by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val mContext = LocalContext.current
    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }
    val mTime = remember { mutableStateOf("") }

    val todoUiState by viewModel.uiState.collectAsState()
    val state by viewModel.state.collectAsState(initial = AddTodoScreenState.None)
    val todoLabels by viewModel.todoLabel.collectAsState()
    val priorities = viewModel.todoPriorities
    val status = viewModel.todoStatus

    Box {
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
                        viewModel.updateTask(it)
                    },
                    singleLine = true
                )
            )

            EditTextField(
                EditTextModel(
                    label = "Description",
                    value = todoUiState.description,
                    onTextChanged = {
                        viewModel.updateDescription(it)
                    },
                    maxLines = 3
                ),
            )

            LargeDropdownMenu(
                DropDownMenuModel(
                    label = "Label",
                    items = todoLabels.convertTodoLabelToDropDownItem(),
                    selectedIndex = 0,
                    onItemSelected = { index ->
                        viewModel.updateLabel(todoLabels[index].id)
                        todoLabels.toMutableStateList().removeAt(index)
                    },
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    trailingIcon = {
                        AddIcon {
                            showDialog = true
                        }
                    },
                    actionItems = dropDownActionItems,
                    onActionItemClick = { actionItem, index ->
                        Log.d("onActionItemClick", "$actionItem $index")
                        viewModel.onDropDownAction(actionItem, index)
                    }
                ),
            )

            LargeDropdownMenu(
                DropDownMenuModel(
                    label = "Priority",
                    items = priorities.convertTodoPrioritiesToDropDownItem(),
                    selectedIndex = 0,
                    onItemSelected = { index ->
                        viewModel.updatePriority(priorities[index].id)
                    },
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth()
                )
            )

            TextViewWithHeader(
                TextViewModel(
                    header = "Date",
                    hint = "Select due date",
                    textValue = mDate.value.ifBlank { null },
                    onClick = {
                        DatePickerDialog(mContext, onDateChange = { date ->
                            mDate.value = date
                            viewModel.updateDate(date)
                        }).show()
                        showDatePicker = true
                    }
                )
            )

            if (mDate.value.isNotEmpty()) {
                TextViewWithHeader(
                    TextViewModel(
                        header = "Time",
                        hint = "Select due time",
                        textValue = mTime.value.ifBlank { null },
                        onClick = {
                            todoTimePickerDialog(mContext, onTimeChange = { time, timeIn24Hours ->
                                mTime.value = time
                                viewModel.updateTime(time)
                            }).show()
                            showDatePicker = true
                        }
                    )
                )
            }

            LargeDropdownMenu(
                DropDownMenuModel(
                    label = "Status",
                    items = status.convertTodoStatusToDropDownItem(),
                    selectedIndex = 0,
                    onItemSelected = { index ->
                        viewModel.updateStatus(priorities[index].id)
                    },
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth()
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ButtonWithOutline(
                        ButtonModel(
                            label = stringResource(id = R.string.cancel),
                            enabled = true,
                            onClick = {
                                onBackPress()
                            })
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ButtonFilled(
                        ButtonModel(
                            label = stringResource(id = R.string.save), enabled = true, onClick = {
                                viewModel.onSave()
                            })
                    )
                }
            }
            //TODO
            TodoDatePicker()
            if (showDialog) {
                AddLabelDialog(dialogModel = DialogModel {
                    showDialog = false
                    if (it) {
                        viewModel.getTodoLabels()
                    }
                })
            }
            when (state) {
                is AddTodoScreenState.Loading -> CircularProgressIndicator()
                is AddTodoScreenState.Completed -> {
                    mContext.toast("Task Added")
                    onBackPress()
                }

                else -> Unit
            }
        }
    }
}