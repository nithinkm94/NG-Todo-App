package com.nkmgb.todoapp.ui.widgets.models

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ButtonModel(
    val label: String,
    val enabled: Boolean = true,
    val onClick: () -> Unit,
    val width: Float = 1f,
    val modifier: Modifier = Modifier
        .fillMaxWidth(width)
        .padding(10.dp)
)
