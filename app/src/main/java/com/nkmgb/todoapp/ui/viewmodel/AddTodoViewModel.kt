package com.nkmgb.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkmgb.todoapp.repository.TodoRepository
import com.nkmgb.todoapp.room.database.TodoItem
import com.nkmgb.todoapp.ui.content.action.AddTodoScreenAction
import com.nkmgb.todoapp.ui.content.reducer.addtodo.AddTodoScreenReducer
import com.nkmgb.todoapp.ui.models.TodoUiState
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
    private val todoRepository: TodoRepository
) : ViewModel() {
    // Todo UI state
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    val state = addTodoScreenReducer.state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            addTodoScreenReducer.update(AddTodoScreenAction.None)
        }
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

    fun updateLabel(label: String) {
        _uiState.value = _uiState.value.copy(
            label = label
        )
    }

    fun updatePriority(priority: String) {
        _uiState.value = _uiState.value.copy(
            priority = priority
        )
    }

    fun onSave() {
        viewModelScope.launch(Dispatchers.Main) {
            addTodoScreenReducer.update(AddTodoScreenAction.Load)
        }
        with(_uiState.value) {
            val todo = TodoItem(
                title = task,
                description = description,
                label = label,
                priority = priority,
                createdDate = Date().time,
                dueDate = Date().time,
                completedDate = Date().time,
                hasNoDueDate = false
            )
            todoRepository.addToDo(todo)
        }

        viewModelScope.launch(Dispatchers.Main) {
            delay(800)
            addTodoScreenReducer.update(AddTodoScreenAction.None)
        }
    }
}
