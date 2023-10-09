package com.nkmgb.todoapp.repository

import com.nkmgb.todoapp.room.dao.TodoLabelDao
import com.nkmgb.todoapp.room.database.TodoLabel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoLabelRepository(private val todoLabelDao: TodoLabelDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addTodoLabel(todoLabel: TodoLabel) {
        coroutineScope.launch(Dispatchers.IO) {
            todoLabelDao.addTodoLabel(todoLabel)
        }
    }

    suspend fun getTodoLabels(): List<TodoLabel> {
        return withContext(Dispatchers.IO) {
            todoLabelDao.getTodoLabels()
        }
    }

    suspend fun deleteTodoLabel(todoLabel: TodoLabel) {
        return withContext(Dispatchers.IO) {
            todoLabelDao.deleteTodoItem(todoLabel)
        }
    }

    suspend fun updateTodoLabel(todoLabel: TodoLabel) {
        return withContext(Dispatchers.IO) {
            todoLabelDao.updateTodo(todoLabel)
        }
    }
}
