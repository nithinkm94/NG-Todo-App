package com.nkmgb.todoapp.ui.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.ui.theme.defaultEditTextStyle
import com.nkmgb.todoapp.ui.widgets.models.DropDownItem
import com.nkmgb.todoapp.ui.widgets.models.DropDownMenuModel

fun getDropDown(items: List<DropDownItem>): SnapshotStateList<DropDownItem> {
    val dropDownItems = mutableStateListOf<DropDownItem>()
    dropDownItems.addAll(items)
    return dropDownItems
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeDropdownMenu(
    dropDownMenuModel: DropDownMenuModel
) {
    var mExpanded by remember { mutableStateOf(false) }

    var selectedItemIndex by remember { mutableStateOf(dropDownMenuModel.selectedIndex) }

    Log.d(
        "onActionItemClick",
        "LargeDropdownMenuItem init"
    )

    var dropDownItems = getDropDown(dropDownMenuModel.items).toMutableStateList()

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    with(dropDownMenuModel) {
        Box(
            modifier = modifier
                .height(IntrinsicSize.Min)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.8f)
                        .clickable(enabled = enabled) {
                            mExpanded = true
                            Log.d("clickable", "clicked")
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        label = { Text(label) },
                        value = dropDownItems.getOrNull(selectedItemIndex)?.name ?: "",
                        enabled = false,
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            Icon(icon, "contentDescription",
                                Modifier.clickable { mExpanded = !mExpanded })
                        },
                        onValueChange = { },
                        readOnly = true,
                        textStyle = defaultEditTextStyle,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                            unfocusedBorderColor = colorResource(id = R.color.purple_200),
                            focusedBorderColor = colorResource(id = R.color.purple_300),
                            disabledBorderColor = colorResource(id = R.color.purple_200),
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    trailingIcon?.invoke()
                }
            }
            // Transparent clickable surface on top of OutlinedTextField
            /*Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .clip(MaterialTheme.shapes.extraSmall),
                color = Color.Transparent,
            ) {}*/
        }

        if (mExpanded && dropDownItems.isNotEmpty()) {
            Dialog(
                onDismissRequest = { mExpanded = false },
            ) {
//            MyTheme {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    val listState = rememberLazyListState()
                    if (selectedItemIndex > -1) {
                        LaunchedEffect("ScrollToSelected") {
                            listState.scrollToItem(index = selectedItemIndex)
                        }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp), state = listState
                    ) {

                        if (notSetLabel != null) {
                            item {
                                LargeDropdownMenuItem(
                                    text = notSetLabel,
                                    selected = false,
                                    enabled = false,
                                    onClick = { },
                                    actionItems = actionItems,
                                    onDropDownActionItemClick = {
                                        Log.d(
                                            "onActionItemClick",
                                            "LargeDropdownMenuItem $selectedItemIndex"
                                        )
                                        onActionItemClick?.invoke(
                                            DropDownActionItem(),
                                            selectedItemIndex
                                        )
                                    }
                                )
                            }
                        }

                        itemsIndexed(dropDownItems) { index, item ->
                            val selectedItem = index == selectedItemIndex
                            drawItem(
                                item,
                                selectedItem,
                                true,
                                {
                                    onItemSelected(index)
                                    selectedItemIndex = index
                                    mExpanded = false
                                },
                                { dropDownAction ->
                                    onActionItemClick?.invoke(dropDownAction, index)
                                    dropDownItems.removeAt(index)
                                    Log.d(
                                        "onActionItemClick",
                                        "dropDownAction ${dropDownAction.action} $index ${dropDownItems.size}"
                                    )
                                }
                            )

                            if (index < dropDownItems.lastIndex) {
                                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    }
                }
//            }
            }
        }
    }
}

@Composable
fun LargeDropdownMenuItem(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
    actionItems: List<DropDownActionItem>? = null,
    onDropDownActionItemClick: (icon: DropDownActionItem) -> Unit
) {
    val contentColor = when {
        !enabled -> MaterialTheme.colorScheme.secondary
        selected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.primary
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(modifier = Modifier
            .clickable(enabled) { onClick() }
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(
                text = text,
                style = defaultEditTextStyle,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .wrapContentSize()
            )
            /*actionItems?.let {
                Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                    it.forEach { actionItem ->
                        Row {
                            IconButton(
                                onClick = {
                                    onDropDownActionItemClick(actionItem)
                                }
                            ) {
                                actionItem.icon?.invoke()
                            }
                        }
                    }
                }
            }*/
        }
    }
}

data class DropDownActionItem(
    val id: String = "",
    val icon: (@Composable () -> Unit)? = null,
    val action: DropDownAction? = null
)

val dropDownActionItems = listOf(
    DropDownActionItem(
        id = "Edit", icon = {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit Label"
            )
        },
        action = DropDownAction.Edit
    ),
    DropDownActionItem(
        id = "Delete", icon = {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete Label"
            )
        },
        action = DropDownAction.Delete
    )
)

sealed class DropDownAction {
    object Edit : DropDownAction()
    object Delete : DropDownAction()
}
