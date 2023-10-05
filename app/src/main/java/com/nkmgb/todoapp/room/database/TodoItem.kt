package com.nkmgb.todoapp.room.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "TodoList")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "label")
    var label: String,

    @ColumnInfo(name = "priority")
    var priority: String,

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
