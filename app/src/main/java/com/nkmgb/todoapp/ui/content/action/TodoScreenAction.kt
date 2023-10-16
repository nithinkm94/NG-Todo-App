package com.nkmgb.todoapp.ui.content.action

import com.nkmgb.todoapp.room.database.jointable.JoinTodoItemTodoLabel
import com.nkmgb.todoapp.ui.state.Action

sealed class TodoScreenAction : Action {
    object Load : TodoScreenAction()
    object Error : TodoScreenAction()
    object None : TodoScreenAction()
    data class Content(
        val content: List<JoinTodoItemTodoLabel>
    ) : TodoScreenAction()

    object Delete : TodoScreenAction()
}
