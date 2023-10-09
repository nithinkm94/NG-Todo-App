package com.nkmgb.todoapp.room.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "TodoLabel")
data class TodoLabel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "color")
    var color: Int,

    @ColumnInfo(name = "icon")
    val icon: Int? = null,

    @ColumnInfo(name = "createdAt")
    var createdAt: Long,

    @ColumnInfo(name = "updatedAt")
    var updatedAt: Long,

    ) : Parcelable
