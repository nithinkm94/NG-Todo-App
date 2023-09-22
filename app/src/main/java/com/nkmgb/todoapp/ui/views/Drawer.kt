package com.nkmgb.todoapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.theme.appThemeColor
import com.nkmgb.todoapp.ui.theme.defaultTextStyle
import com.nkmgb.todoapp.utils.AppScreens


private val screens = listOf(
    AppScreens.HomeScreen,
    AppScreens.TodoList,
    AppScreens.Settings
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxWidth(0.6f)
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.white)),
    ) {
        Row(
            modifier
                .background(color = appThemeColor.primary)
                .fillMaxWidth()
                .padding(start = 0.dp, end = 0.dp, bottom = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "TodoApp",
                modifier= Modifier.weight(1f)
                    .padding(start = 6.dp),
                textAlign = TextAlign.Start,
                style = defaultTextStyle
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Icon(
                Icons.Default.Close,
                contentDescription = "Close",
                modifier= Modifier.weight(0.5f),
                tint = colorResource(id = R.color.white),
            )
        }
        screens.forEach { screen ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onDestinationClicked(screen.route) })
                    .height(45.dp)
                    .padding(start = 10.dp)
            ) {
                Icon(
                    screen.icon,
                    contentDescription = screen.title,
                    tint = colorResource(id = R.color.purple_200)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = screen.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                )
            }
            Divider(color = Color.LightGray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Developed by Nithin Kumar M \nV (1.0)",
            color = Color.Gray,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}