package com.nkmgb.todoapp.ui.views.labels.model

data class DialogModel(
    val onDismissRequest: (labelUpdated: Boolean) -> Unit
)