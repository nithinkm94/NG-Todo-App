package com.nkmgb.todoapp.ui.content.action

import com.nkmgb.todoapp.ui.state.Action
import com.nkmgb.todoapp.room.database.TodoItem

sealed class TodoScreenAction : Action {
    object Load : TodoScreenAction()
    object Error : TodoScreenAction()
    object None : TodoScreenAction()
    data class Content(
        val content: List<TodoItem>
    ) : TodoScreenAction()

    object Delete : TodoScreenAction()
}
