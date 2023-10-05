package com.nkmgb.todoapp.ui.models

data class TodoUiState(
    val task: String = "",
    val taskErrors: MutableList<String> = mutableListOf(),
    val description: String = "",
    val info: String = "",
    val label: String = "",
    val priority: String = "",
    val date: String = "",
    val time: String = ""
)
