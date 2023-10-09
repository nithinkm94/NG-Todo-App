package com.nkmgb.todoapp.ui.mappers

import com.nkmgb.todoapp.localdata.models.Priority
import com.nkmgb.todoapp.room.database.TodoLabel
import com.nkmgb.todoapp.ui.widgets.models.DropDownItem

object Mappers {
    /*    fun <T> mapItemsToDropDownItem(item: T) {
            when (item) {
                is TodoLabel -> item.convertTodoLabelToDropDownItem()
            }
        }*/

    fun List<TodoLabel>.convertTodoLabelToDropDownItem(): List<DropDownItem> {
        val dropDownItems = mutableListOf<DropDownItem>()
        this.forEach {
            dropDownItems.add(DropDownItem(id = it.id, name = it.name))
        }
        return dropDownItems
    }

    fun List<Priority>.convertTodoPrioritiesToDropDownItem(): List<DropDownItem> {
        val dropDownItems = mutableListOf<DropDownItem>()
        this.forEach {
            dropDownItems.add(DropDownItem(id = it.id, name = it.name))
        }
        return dropDownItems
    }
}