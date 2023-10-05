package com.nkmgb.todoapp.ui.content.reducer.addtodo

import com.nkmgb.todoapp.ui.content.action.AddTodoScreenAction
import com.nkmgb.todoapp.ui.content.state.AddTodoScreenState
import kotlinx.coroutines.flow.MutableSharedFlow


class AddTodoScreenReducerImpl : AddTodoScreenReducer {

    /**
     * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-mutable-shared-flow/
     */
    override val state = MutableSharedFlow<AddTodoScreenState>()

    override suspend fun update(action: AddTodoScreenAction) {
        when (action) {
            is AddTodoScreenAction.Load -> AddTodoScreenState.Loading
            is AddTodoScreenAction.Error -> AddTodoScreenState.Error("Error")
            is AddTodoScreenAction.None -> AddTodoScreenState.None
            is AddTodoScreenAction.Complete -> AddTodoScreenState.Completed
        }.also {
            state.emit(it)
        }
    }
}
