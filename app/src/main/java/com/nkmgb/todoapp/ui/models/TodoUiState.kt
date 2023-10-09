package com.nkmgb.todoapp.ui.models

data class TodoUiState(
    val task: String = "",
    val taskErrors: MutableList<String> = mutableListOf(),
    val description: String = "",
    val info: String = "",
    val label: Int = 0,
    val priority: Int = 0,
    val date: String = "",
    val time: String = ""
)
