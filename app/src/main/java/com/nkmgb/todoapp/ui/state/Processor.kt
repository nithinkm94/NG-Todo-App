package com.nkmgb.todoapp.ui.state

fun interface Processor<S : State, A : Action> {
    suspend fun process(state: S, action: A)
}