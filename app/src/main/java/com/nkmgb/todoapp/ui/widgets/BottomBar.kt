package com.nkmgb.todoapp.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nkmgb.todoapp.ui.widgets.models.ButtonModel

@Composable
fun BottomBar(label: String, addButtonClicked: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ButtonWithOutline(
            buttonModel = ButtonModel(
                label = label,
                onClick = addButtonClicked,
                width = 0.5f
            )
        )
    }
}
