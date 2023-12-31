package com.nkmgb.todoapp.repository

import androidx.lifecycle.MutableLiveData
import com.nkmgb.todoapp.room.dao.TodoDao
import com.nkmgb.todoapp.room.database.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoRepository(private val todoDao: TodoDao) {

    val todoList = MutableLiveData<List<TodoItem>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addToDo(newTodo: TodoItem) {
        coroutineScope.launch(Dispatchers.IO) {
            todoDao.addTodo(newTodo)
        }
    }

    suspend fun getToDoList(): List<TodoItem> {
        return withContext(Dispatchers.IO) {
            todoDao.getTodoList()
        }
    }

    suspend fun deleteToDoItem(todoItem: TodoItem) {
        return withContext(Dispatchers.IO) {
            todoDao.deleteTodoItem(todoItem)
        }
    }

    fun getAllEmployees() {
        coroutineScope.launch(Dispatchers.IO) {
            todoList.postValue(todoDao.getTodoList())
        }
    }

}
