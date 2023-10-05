package com.nkmgb.todoapp.ui.models

data class ButtonModel(
    val label: String,
    val enabled: Boolean = true,
    val onClick: () -> Unit
)
