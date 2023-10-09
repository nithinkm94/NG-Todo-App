package com.nkmgb.todoapp.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.theme.TodoAppTheme
import com.nkmgb.todoapp.ui.theme.defaultEditTextStyle
import com.nkmgb.todoapp.ui.widgets.models.TextViewModel

@Composable
fun TextViewWithHeader(model: TextViewModel) {
    with(model) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                text = header,
                color = Color.Black
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
                    .height(50.dp)
                    .background(
                        color = colorResource(id = R.color.purple_transparent),
                        shape = RoundedCornerShape(3.dp)
                    )
                    .clickable {
                        onClick()
                    }
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 10.dp),
                    text = textValue ?: hint,
                    color = if (textValue.isNullOrEmpty()) Color.Gray else colorResource(id = R.color.purple_500),
                    style = defaultEditTextStyle.copy(background = colorResource(id = R.color.purple_transparent)),
                    textAlign = TextAlign.Start
                )
                IconButton(
                    onClick = onClick,
                    modifier = Modifier
                        .padding(all = 10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                        tint = colorResource(id = R.color.purple_200),
                        contentDescription = "Calendar"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppTheme {
        TextViewWithHeader(
            TextViewModel(
                header = "Date",
                hint = "Click here to select date",
                onClick = {}
            )
        )
    }
}
