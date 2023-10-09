package com.nkmgb.todoapp.ui.widgets.models

data class TextViewModel(
    val header: String,
    val hint: String,
    val textValue: String? = null,
    val enabled: Boolean = true,
    val onClick: () -> Unit
)
