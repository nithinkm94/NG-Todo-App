package com.nkmgb.todoapp.room.dao

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import com.nkmgb.todoapp.room.database.TodoList
import kotlinx.parcelize.Parcelize

@Dao
interface TodoDao {

    @Query("SELECT * FROM todolist")
    fun getTodoList(): List<TodoList>

}