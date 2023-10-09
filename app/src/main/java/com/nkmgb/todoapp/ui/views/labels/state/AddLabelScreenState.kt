package com.nkmgb.todoapp.ui.views.labels.state

import com.nkmgb.todoapp.ui.state.State

sealed class AddLabelScreenState : State {

    object Loading : AddLabelScreenState()

    data class Error(val errorMessage: String) : AddLabelScreenState()

    object None : AddLabelScreenState()

    object Completed : AddLabelScreenState()
}
