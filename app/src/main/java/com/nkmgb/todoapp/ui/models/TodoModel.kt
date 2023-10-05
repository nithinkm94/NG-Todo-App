package com.nkmgb.todoapp.ui.models

data class TodoModel(
    val task: String = "",
    val description: String = "",
    val info: String = "",
    val label: String = "",
    val priority: String = "",
    val date: String = "",
    val time: String = ""
)