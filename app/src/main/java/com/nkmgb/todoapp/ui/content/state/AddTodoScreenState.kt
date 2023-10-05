package com.nkmgb.todoapp.ui.content.state

import com.nkmgb.todoapp.ui.state.State

sealed class AddTodoScreenState : State {

    object Loading : AddTodoScreenState()

    data class Error(val errorMessage: String) : AddTodoScreenState()

    object None : AddTodoScreenState()

    object Completed : AddTodoScreenState()
}
