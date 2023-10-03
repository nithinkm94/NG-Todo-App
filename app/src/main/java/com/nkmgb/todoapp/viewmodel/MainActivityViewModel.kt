package com.nkmgb.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nkmgb.todoapp.repository.TodoRepository
import com.nkmgb.todoapp.room.database.TodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {
    fun getButtonString(): String = "Test String"

    val todoList: LiveData<List<TodoItem>> = todoRepository.todoList

}