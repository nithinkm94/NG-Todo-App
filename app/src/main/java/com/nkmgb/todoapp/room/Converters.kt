package com.nkmgb.todoapp.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nkmgb.todoapp.localdata.models.Priority
import com.nkmgb.todoapp.room.database.TodoLabel

class Converters {
    @TypeConverter
    fun fromLabel(value: TodoLabel): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toLabel(label: String): TodoLabel {
        return Gson().fromJson(label, TodoLabel::class.java)
    }

    @TypeConverter
    fun fromPriority(value: Priority): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Gson().fromJson(priority, Priority::class.java)
    }
}