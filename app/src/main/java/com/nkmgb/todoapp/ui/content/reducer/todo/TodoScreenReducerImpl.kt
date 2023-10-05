package com.nkmgb.todoapp.ui.content.reducer.todo

import com.nkmgb.todoapp.ui.content.action.TodoScreenAction
import com.nkmgb.todoapp.ui.content.state.TodoScreenState
import kotlinx.coroutines.flow.MutableSharedFlow


class TodoScreenReducerImpl : TodoScreenReducer {

    /**
     * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-mutable-shared-flow/
     */
    override val state = MutableSharedFlow<TodoScreenState>()

    override suspend fun update(action: TodoScreenAction) {
        when (action) {
            is TodoScreenAction.Load -> TodoScreenState.Loading
            is TodoScreenAction.Error -> TodoScreenState.Error("Error")
            is TodoScreenAction.None -> TodoScreenState.None
            is TodoScreenAction.Content -> TodoScreenState.Content(content = action.content)
            is TodoScreenAction.Delete -> TodoScreenState.Delete
        }.also {
            state.emit(it)
        }
    }
}
