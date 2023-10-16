package com.nkmgb.todoapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nkmgb.todoapp.repository.TodoRepository
import com.nkmgb.todoapp.room.database.TodoItem
import com.nkmgb.todoapp.room.database.jointable.JoinTodoItemTodoLabel
import com.nkmgb.todoapp.ui.content.action.TodoScreenAction
import com.nkmgb.todoapp.ui.content.reducer.todo.TodoScreenReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoScreenReducer: TodoScreenReducer,
    private val todoRepository: TodoRepository
) : ViewModel(), DefaultLifecycleObserver {
    val state = todoScreenReducer.state

    private val items = mutableStateListOf<JoinTodoItemTodoLabel>()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            updateAction(TodoScreenAction.Load)
        }
    }

    private fun getTodoList() {
        viewModelScope.launch(Dispatchers.Main) {
            val data = todoRepository.getTodoListImproved()
            items.clear()
            items.addAll(data)
            updateAction(TodoScreenAction.Content(data))

            val improved = todoRepository.getToDoList()
            Log.d("improved", Gson().toJson(improved))
            Log.d("improved", Gson().toJson(data))
        }
    }

    private suspend fun updateAction(action: TodoScreenAction) {
        todoScreenReducer.update(action)
    }

    fun delete(todoItem: TodoItem) {
        /*viewModelScope.launch(Dispatchers.Main) {
            todoRepository.deleteToDoItem(todoItem)
            items.remove(todoItem)
            todoScreenReducer.update(TodoScreenAction.Content(items))
        }*/
    }

    override fun onResume(owner: LifecycleOwner) {
        getTodoList()
    }

    override fun onStart(owner: LifecycleOwner) {
        loadIndicator()
    }

    private fun loadIndicator() {
        viewModelScope.launch(Dispatchers.Main) {
            updateAction(TodoScreenAction.Load)
        }
    }
}
