package com.nkmgb.todoapp.ui.content.action

import com.nkmgb.todoapp.ui.state.Action

sealed class AddTodoScreenAction : Action {
    object Load : AddTodoScreenAction()
    object Error : AddTodoScreenAction()
    object None : AddTodoScreenAction()
    object Complete : AddTodoScreenAction()
}
