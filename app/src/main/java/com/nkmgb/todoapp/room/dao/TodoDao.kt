package com.nkmgb.todoapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nkmgb.todoapp.room.database.TodoItem

@Dao
interface TodoDao {
    @Query("SELECT * FROM todolist ORDER BY (dueDate) ASC")
    fun getTodoList(): List<TodoItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todoItem: TodoItem)

    @Query("SELECT * FROM todolist WHERE id = :id")
    fun findEmployeeById(id: Int): TodoItem

    @Update
    suspend fun updateTodo(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)

}
