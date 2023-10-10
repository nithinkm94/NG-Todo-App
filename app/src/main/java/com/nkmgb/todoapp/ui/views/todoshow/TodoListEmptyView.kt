package com.nkmgb.todoapp.ui.views.todoshow

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.theme.text16

@Composable
fun TodoListEmptyView() {
    AnimatedVisibility(visible = true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Looks like you have no todos...",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = text16
            )
            Image(
                painter = painterResource(id = R.drawable.empty_todo_list),
                contentDescription = "Empty todo list",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
