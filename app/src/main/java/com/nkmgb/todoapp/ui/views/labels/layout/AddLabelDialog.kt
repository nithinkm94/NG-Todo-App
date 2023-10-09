package com.nkmgb.todoapp.ui.views.labels.layout

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.localdata.LocalData.getLabelColors
import com.nkmgb.todoapp.ui.content.state.AddTodoScreenState
import com.nkmgb.todoapp.ui.views.labels.model.DialogModel
import com.nkmgb.todoapp.ui.views.labels.viewmodel.AddTodoLabelViewModel
import com.nkmgb.todoapp.ui.widgets.ButtonFilled
import com.nkmgb.todoapp.ui.widgets.EditTextField
import com.nkmgb.todoapp.ui.widgets.models.ButtonModel
import com.nkmgb.todoapp.ui.widgets.models.EditTextModel

@Composable
fun AddLabelDialog(dialogModel: DialogModel, viewModel: AddTodoLabelViewModel = hiltViewModel()) {
    val todoUiState by viewModel.uiState.collectAsState()
    val state by viewModel.state.collectAsState(initial = AddTodoScreenState.None)
    with(dialogModel) {
        Dialog(onDismissRequest = { onDismissRequest(false) }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column {
                    Text(
                        text = "Add labels add colors",
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .padding(10.dp),
                        textAlign = TextAlign.Center,
                    )
                    EditTextField(
                        EditTextModel(
                            label = "Label Name",
                            value = todoUiState.name,
                            error = todoUiState.errors,
                            onTextChanged = {
                                viewModel.updateLabelName(it)
                            },
                            singleLine = true
                        )
                    )
                    LazyRow(modifier = Modifier
                        .padding(top = 10.dp), content = {
                        items(getLabelColors) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                            ) {
                                Circle(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .clickable {
                                            viewModel.updateColor(it)
                                        },
                                    colorResource(id = it)
                                )
                                if (todoUiState.color == it) {
                                    Icon(
                                        modifier = Modifier.align(Alignment.Center),
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = "Navigation icon",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    })
                    ButtonFilled(
                        ButtonModel(
                            label = stringResource(id = R.string.save),
                            enabled = true,
                            onClick = {
                                viewModel.onSave()
                            },
                            width = 0.8f,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 15.dp, horizontal = 10.dp)
                        )
                    )
                }
                when (state) {
                    is AddTodoScreenState.Loading -> CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterHorizontally)
                    )

                    is AddTodoScreenState.Completed -> {
                        onDismissRequest(true)
                    }

                    else -> Unit
                }
            }
        }
    }
}

@Composable
fun Circle(modifier: Modifier, color: Color) {
    Canvas(modifier = modifier
        .size(50.dp)
        .padding(5.dp), onDraw = {
        drawCircle(color = color)
    })
}