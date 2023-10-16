package com.nkmgb.todoapp.ui.content.state

import com.nkmgb.todoapp.room.database.jointable.JoinTodoItemTodoLabel
import com.nkmgb.todoapp.ui.state.State

sealed class TodoScreenState : State {

    object Loading : TodoScreenState()

    data class Content(
        val content: List<JoinTodoItemTodoLabel>
    ) : TodoScreenState()

    data class Error(val errorMessage: String) : TodoScreenState()

    object None : TodoScreenState()

    object Delete : TodoScreenState()
}
