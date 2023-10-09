package com.nkmgb.todoapp.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nkmgb.todoapp.R

@Composable
fun AddIcon(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.padding(all = 10.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_add_circle_24),
            tint = colorResource(id = R.color.purple_200),
            contentDescription = "Calendar"
        )
    }
}