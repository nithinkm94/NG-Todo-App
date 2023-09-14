package com.nkmgb.todoapp.room.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "TodoList")
data class TodoList(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "todoTitle")
    var todoTitle: String,

    @ColumnInfo(name = "todoDescription")
    var todoDescription: String,
) : Parcelable
