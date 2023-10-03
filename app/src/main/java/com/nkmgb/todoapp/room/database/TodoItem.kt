package com.nkmgb.todoapp.room.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "TodoList")
data class TodoItem(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "todoTitle")
    var todoTitle: String,

    @ColumnInfo(name = "todoDescription")
    var todoDescription: String,

    @ColumnInfo(name = "hasNoDueDate")
    var hasNoDueDate: Boolean,

    @ColumnInfo(name = "createdDate")
    var createdDate: Long,

    @ColumnInfo(name = "dueDate")
    var dueDate: Long,

    @ColumnInfo(name = "completedDate")
    var completedDate: Long,

    // labels

    //

    // link status

// category

// priority

) : Parcelable
