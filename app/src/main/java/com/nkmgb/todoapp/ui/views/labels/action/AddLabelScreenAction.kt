package com.nkmgb.todoapp.ui.views.labels.action

import com.nkmgb.todoapp.ui.state.Action

sealed class AddLabelScreenAction : Action {
    object Load : AddLabelScreenAction()
    object Error : AddLabelScreenAction()
    object None : AddLabelScreenAction()
    object Complete : AddLabelScreenAction()
}
