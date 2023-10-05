package com.nkmgb.todoapp.ui.content.state

import com.nkmgb.todoapp.room.database.TodoItem
import com.nkmgb.todoapp.ui.state.State

sealed class TodoScreenState : State {

    object Loading : TodoScreenState()

    data class Content(
        val content: List<TodoItem>
    ) : TodoScreenState()

    data class Error(val errorMessage: String) : TodoScreenState()

    object None : TodoScreenState()

    object Delete : TodoScreenState()
}
