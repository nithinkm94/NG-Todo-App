package com.nkmgb.todoapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkmgb.todoapp.localdata.LocalData
import com.nkmgb.todoapp.localdata.models.Priority
import com.nkmgb.todoapp.localdata.models.Status
import com.nkmgb.todoapp.repository.TodoLabelRepository
import com.nkmgb.todoapp.repository.TodoRepository
import com.nkmgb.todoapp.room.database.TodoItem
import com.nkmgb.todoapp.room.database.TodoLabel
import com.nkmgb.todoapp.ui.content.action.AddTodoScreenAction
import com.nkmgb.todoapp.ui.content.reducer.addtodo.AddTodoScreenReducer
import com.nkmgb.todoapp.ui.models.TodoUiState
import com.nkmgb.todoapp.ui.widgets.DropDownAction
import com.nkmgb.todoapp.ui.widgets.DropDownActionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val addTodoScreenReducer: AddTodoScreenReducer,
    private val todoRepository: TodoRepository,
    private val todoLabelRepository: TodoLabelRepository
) : ViewModel(), DefaultLifecycleObserver {
    // Todo UI state
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    private val _todoLabels = MutableStateFlow(listOf<TodoLabel>())
    val todoLabel: StateFlow<List<TodoLabel>> = _todoLabels.asStateFlow()

    private val _todoPriorities = mutableStateListOf<Priority>()
    val todoPriorities: SnapshotStateList<Priority> = _todoPriorities

    private val _todoStatus = mutableStateListOf<Status>()
    val todoStatus: SnapshotStateList<Status> = _todoStatus

    val state = addTodoScreenReducer.state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            addTodoScreenReducer.update(AddTodoScreenAction.None)
        }
        getTodoLabels()
        getTodoPriorities()
        getTodoStatus()
    }

    fun updateTask(task: String) {
        val errors = mutableListOf<String>()
        if (task.isEmpty()) {
            errors.add("The task must not be empty.")
        }
        _uiState.value = _uiState.value.copy(
            task = task,
            taskErrors = errors
        )
    }

    fun updateDescription(task: String) {
        _uiState.value = _uiState.value.copy(
            description = task
        )
    }

    fun updateInfo(task: String) {
        _uiState.value = _uiState.value.copy(
            info = task
        )
    }

    fun updateLabel(label: TodoLabel) {
        _uiState.value = _uiState.value.copy(
            label = label
        )
    }

    fun updatePriority(priority: Priority) {
        _uiState.value = _uiState.value.copy(
            priority = priority
        )
    }

    fun updateStatus(status: Int) {
        _uiState.value = _uiState.value.copy(
            status = status
        )
    }

    fun updateDate(date: String) {
        _uiState.value = _uiState.value.copy(
            date = date
        )
    }

    fun updateTime(time: String) {
        _uiState.value = _uiState.value.copy(
            time = time
        )
    }

    fun getTodoLabels() {
        viewModelScope.launch {
            _todoLabels.value = todoLabelRepository.getTodoLabels()
            if (_todoLabels.value.isNotEmpty()) {
                updateLabel(_todoLabels.value[0])
            }
        }
    }

    fun deleteLabel(label: TodoLabel) {
        viewModelScope.launch {
            todoLabelRepository.deleteTodoLabel(label)
            _todoLabels.compareAndSet(
                _todoLabels.value,
                _todoLabels.value.toMutableStateList().apply { remove(label) })
        }
    }

    fun updateTodoLabel(label: TodoLabel) {
        viewModelScope.launch {
            todoLabelRepository.updateTodoLabel(label)
        }
    }

    private fun getTodoPriorities() {
        viewModelScope.launch {
            _todoPriorities.addAll(LocalData.getPriority)
            if (_todoPriorities.isNotEmpty()) {
                updatePriority(_todoPriorities[0])
            }
        }
    }

    private fun getTodoStatus() {
        viewModelScope.launch {
            _todoStatus.addAll(LocalData.getStatus)
        }
    }


    fun onSave() {
        viewModelScope.launch(Dispatchers.Main) {
            addTodoScreenReducer.update(AddTodoScreenAction.Load)
            with(_uiState.value) {
                if (task.isNotEmpty()) {
                    val todo = TodoItem(
                        title = task,
                        description = description,
                        label = label,
                        priority = priority,
                        createdAt = Date().time,
                        dueDate = date,
                        hasNoDueDate = date.isEmpty(),
                        updatedAt = Date().time,
                        status = status
                    )
                    todoRepository.addToDo(todo)
                    delay(300)
                    addTodoScreenReducer.update(AddTodoScreenAction.Complete)
                } else {
                    updateTask("")
                    addTodoScreenReducer.update(AddTodoScreenAction.Error)
                }
            }
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d("onResume", "onResume")
        getTodoLabels()
    }

    fun onDropDownAction(actionItem: DropDownActionItem, index: Int) {
        when (actionItem.action) {
            DropDownAction.Edit -> updateTodoLabel(todoLabel.value[index])
            DropDownAction.Delete -> deleteLabel(todoLabel.value[index])
            else -> {}
        }
    }
}
