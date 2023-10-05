package com.nkmgb.todoapp.ui.models

data class EditTextModel(
    val label: String,
    val hint: String = "",
    val maxLines: Int = 1,
    val value: String,
    val error: MutableList<String>? = null,
    val onTextChanged: (String) -> Unit,
    val singleLine: Boolean = false
)
