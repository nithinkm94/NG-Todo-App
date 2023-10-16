package com.nkmgb.todoapp.ui.models

import com.nkmgb.todoapp.localdata.models.Priority
import com.nkmgb.todoapp.room.database.TodoLabel

data class TodoUiState(
    val task: String = "",
    val taskErrors: MutableList<String> = mutableListOf(),
    val description: String = "",
    val info: String = "",
    val label: TodoLabel? = null,
    val priority: Priority? = null,
    val status: Int = 0,
    val date: String = "",
    val time: String = ""
)
