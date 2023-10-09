package com.nkmgb.todoapp.ui.views.labels.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkmgb.todoapp.repository.TodoLabelRepository
import com.nkmgb.todoapp.room.database.TodoLabel
import com.nkmgb.todoapp.ui.content.action.AddTodoScreenAction
import com.nkmgb.todoapp.ui.views.labels.model.TodoLabelUiState
import com.nkmgb.todoapp.ui.views.labels.reducer.AddLabelScreenReducer
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
class AddTodoLabelViewModel @Inject constructor(
    private val addLabelScreenReducer: AddLabelScreenReducer,
    private val todoLabelRepository: TodoLabelRepository
) : ViewModel() {
    // Todo UI state
    private val _uiState = MutableStateFlow(TodoLabelUiState())
    val uiState: StateFlow<TodoLabelUiState> = _uiState.asStateFlow()

    private val _todoLabels = MutableStateFlow(listOf<TodoLabel>())
    val todoLabel: StateFlow<List<TodoLabel>> = _todoLabels.asStateFlow()

    private val _todoLabelsState = mutableStateListOf<TodoLabel>()
    val todoLabelState: SnapshotStateList<TodoLabel> = _todoLabelsState

    val state = addLabelScreenReducer.state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            addLabelScreenReducer.update(AddTodoScreenAction.None)
        }
        getTodoLabels()
    }

    fun updateLabelName(labelName: String) {
        val errors = mutableListOf<String>()
        if (labelName.isEmpty()) {
            errors.add("The label name must not be empty.")
            _uiState.value = _uiState.value.copy(
                errors = errors,
                name = ""
            )
        } else if (labelName.contains(" ")) {
            return
        } else {
            _uiState.value = _uiState.value.copy(
                name = labelName.replaceFirstChar {
                    it.uppercase()
                },
                errors = mutableListOf()
            )
            return
        }
    }

    fun updateColor(color: Int) {
        val errors = mutableListOf<String>()
        if (color == 0) {
            errors.add("The color must not be empty.")
        }
        _uiState.value = _uiState.value.copy(
            color = color,
            errors = errors
        )
    }

    private fun getTodoLabels() {
        viewModelScope.launch {
            _todoLabels.value = todoLabelRepository.getTodoLabels()
//            _todoLabelsState.addAll(todoLabelRepository.getTodoLabels())
        }
    }

    private fun reset() {
        _uiState.value = _uiState.value.copy(
            name = "",
            errors = mutableListOf()
        )
    }

    fun onSave() {
        viewModelScope.launch(Dispatchers.Main) {
            addLabelScreenReducer.update(AddTodoScreenAction.Load)
            with(_uiState.value) {
                if (name.isNotEmpty()) {
                    val todoLabel = TodoLabel(
                        name = name,
                        color = color,
                        createdAt = Date().time,
                        updatedAt = Date().time,
                    )
                    todoLabelRepository.addTodoLabel(todoLabel)
                    reset()
                    delay(300)
                    addLabelScreenReducer.update(AddTodoScreenAction.Complete)
                } else {
                    updateLabelName("")
                    updateColor(0)
                    addLabelScreenReducer.update(AddTodoScreenAction.Error)
                }
            }
        }
    }
}
