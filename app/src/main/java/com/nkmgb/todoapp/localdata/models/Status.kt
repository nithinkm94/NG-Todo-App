package com.nkmgb.todoapp.localdata.models

data class Status(
    val id: Int,
    val name: String,
    val color: Int,
    val icon: Int? = null
)
