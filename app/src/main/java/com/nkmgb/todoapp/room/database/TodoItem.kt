package com.nkmgb.todoapp.room.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nkmgb.todoapp.localdata.models.Priority
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
    var label: TodoLabel?,

    @ColumnInfo(name = "priority")
    var priority: Priority?,

    @ColumnInfo(name = "status")
    var status: Int,

    @ColumnInfo(name = "hasNoDueDate")
    var hasNoDueDate: Boolean,

    @ColumnInfo(name = "dueDate")
    var dueDate: String,

    @ColumnInfo(name = "dueTime")
    var dueTime: String? = null,

    @ColumnInfo(name = "completedAt")
    var completedAt: Long? = null,

    @ColumnInfo(name = "createdAt")
    var createdAt: Long,

    @ColumnInfo(name = "updatedAt")
    var updatedAt: Long,

//    @ColumnInfo(name = "date_of_birth")
//    @TypeConverters(Converters::class)
//    var dob: Date?
    // labels

    //

    // link status

// category

// priority

) : Parcelable

