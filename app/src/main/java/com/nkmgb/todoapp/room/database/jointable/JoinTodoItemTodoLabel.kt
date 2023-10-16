package com.nkmgb.todoapp.room.database.jointable

import androidx.room.Embedded
import androidx.room.Relation
import com.nkmgb.todoapp.room.database.TodoItem
import com.nkmgb.todoapp.room.database.TodoLabel

data class JoinTodoItemTodoLabel(
    @Embedded
    val todoLabel: TodoLabel,
    @Relation(
        parentColumn = "id",
        entityColumn = "label"
    )
    val todoItem: TodoItem
)
