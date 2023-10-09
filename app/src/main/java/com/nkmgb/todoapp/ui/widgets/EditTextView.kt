package com.nkmgb.todoapp.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.theme.defaultEditTextStyle
import com.nkmgb.todoapp.ui.widgets.models.EditTextModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(
    editTextModel: EditTextModel
) {
    var text by rememberSaveable { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    with(editTextModel) {
        Column {
            OutlinedTextField(
                value = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onValueChange = {
                    text = it
                    onTextChanged(it)
                },
                singleLine = singleLine,
                maxLines = maxLines,
                label = { Text(label) },
                textStyle = defaultEditTextStyle,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                    unfocusedBorderColor = colorResource(id = R.color.purple_200),
                    focusedBorderColor = colorResource(id = R.color.purple_300),
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Gray
                ),
                interactionSource = interactionSource,
                shape = RoundedCornerShape(8.dp)
//                isError = isFocused && error != null
            )
            error?.forEach {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                    text = it,
                    color = Color.Red
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    inputWrapper: String,
    @StringRes labelResId: Int,
    maxLength: Int,
    maxLines: Int,
    onTextChanged: (String) -> Unit
) {
    var fieldValue by remember { mutableStateOf(inputWrapper) }
    val focusManager = LocalFocusManager.current
    Column {
        OutlinedTextField(
            value = fieldValue,
            label = { Text(stringResource(labelResId), style = defaultEditTextStyle) },
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            modifier = modifier,
            onValueChange = {
                if (it.length <= maxLength) {
                    fieldValue = it
                    //text = value.filter { it.isDigit() }
                    onTextChanged(it)
                }
            },
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                onDone = {
                    focusManager.clearFocus()
                }
            ),
        )

    }
}
