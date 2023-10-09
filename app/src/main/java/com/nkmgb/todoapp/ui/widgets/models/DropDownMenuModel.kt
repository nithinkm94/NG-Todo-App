package com.nkmgb.todoapp.ui.widgets.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nkmgb.todoapp.ui.widgets.DropDownActionItem
import com.nkmgb.todoapp.ui.widgets.LargeDropdownMenuItem

data class DropDownMenuModel(
    val modifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val label: String,
    val notSetLabel: String? = null,
    val items: List<DropDownItem>,
    val selectedIndex: Int = -1,
    val onItemSelected: (index: Int) -> Unit,
    val actionItems: List<DropDownActionItem>? = null,
    val onActionItemClick: ((actionItem: DropDownActionItem, index: Int) -> Unit)? = null,
    val drawItem: @Composable (DropDownItem, Boolean, Boolean, () -> Unit, (icon: DropDownActionItem) -> Unit) -> Unit = { item, selected, itemEnabled, onClick, onDropDownActionItemClick ->
        LargeDropdownMenuItem(
            text = item.name,
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
            actionItems = actionItems,
            onDropDownActionItemClick = onDropDownActionItemClick
        )
    },
    val trailingIcon: @Composable (() -> Unit)? = null,
)