package com.nkmgb.todoapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nkmgb.todoapp.room.database.TodoItem
import com.nkmgb.todoapp.room.database.jointable.JoinTodoItemTodoLabel

@Dao
interface TodoDao {
    @Query("SELECT * FROM todolist ORDER BY (id) ASC")
    fun getTodoList(): List<TodoItem>

    @Query("SELECT TodoList.*, TodoLabel.* FROM TodoList INNER JOIN TodoLabel ON TodoList.label = TodoLabel.id")
    fun getTodoItemLabelListImproved(): List<JoinTodoItemTodoLabel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todoItem: TodoItem)

    @Query("SELECT * FROM todolist WHERE id = :id")
    fun findEmployeeById(id: Int): TodoItem

    @Update
    suspend fun updateTodo(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)

}
