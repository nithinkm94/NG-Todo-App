package com.nkmgb.todoapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nkmgb.todoapp.room.database.TodoLabel

@Dao
interface TodoLabelDao {
    @Query("SELECT * FROM todoLabel")
    fun getTodoLabels(): List<TodoLabel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodoLabel(todoLabel: TodoLabel)

    @Query("SELECT * FROM todoLabel WHERE id = :id")
    fun findLabelById(id: Int): TodoLabel

    @Update
    suspend fun updateTodo(todoLabel: TodoLabel)

    @Delete
    suspend fun deleteTodoItem(todoLabel: TodoLabel)

}
