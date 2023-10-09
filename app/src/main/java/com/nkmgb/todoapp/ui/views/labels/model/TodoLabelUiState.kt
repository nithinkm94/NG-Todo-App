package com.nkmgb.todoapp.ui.views.labels.model

import com.nkmgb.todoapp.R

data class TodoLabelUiState(
    val name: String = "",
    val color: Int = R.color.label_default,
    val errors: MutableList<String> = mutableListOf(),
)
