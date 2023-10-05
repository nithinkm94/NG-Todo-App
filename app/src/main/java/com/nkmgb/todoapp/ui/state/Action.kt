package com.nkmgb.todoapp.ui.state

interface Action

interface ActionWithAfterHooks<S : State, A : Action> : Action {
    val afterStateUpdateHooks: List<Processor<S, A>>
}

interface ActionWithBeforeHooks<S : State, A : Action> : Action {
    val beforeStateUpdateHooks: List<Processor<S, A>>
}