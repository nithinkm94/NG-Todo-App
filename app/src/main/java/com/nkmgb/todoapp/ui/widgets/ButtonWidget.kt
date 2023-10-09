package com.nkmgb.todoapp.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.widgets.models.ButtonModel

@Composable
fun ButtonWithOutline(
    buttonModel: ButtonModel
) {
    with(buttonModel) {
        Button(
            onClick = { onClick() },
            enabled = enabled,
            border = BorderStroke(1.dp, colorResource(id = R.color.purple_200)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(width)
                .padding(10.dp)
        ) {
            Text(text = label)
        }
    }
}

@Composable
fun ButtonFilled(
    buttonModel: ButtonModel
) {
    with(buttonModel) {
        Button(
            onClick = { onClick() },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.purple_300),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            modifier = modifier
        ) {
            Text(text = label)
        }
    }
}