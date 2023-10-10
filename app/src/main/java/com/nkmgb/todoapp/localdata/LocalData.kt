package com.nkmgb.todoapp.localdata

import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.localdata.models.Priority
import com.nkmgb.todoapp.localdata.models.Status

object LocalData {
    val getPriority = mutableListOf<Priority>().apply {
        add(Priority(id = 0, name = "Default", color = R.color.priority_default))
        add(Priority(id = 0, name = "Low", color = R.color.priority_low))
        add(Priority(id = 0, name = "Medium", color = R.color.priority_medium))
        add(Priority(id = 0, name = "High", color = R.color.priority_high))
    }

    val getLabelColors = mutableListOf<Int>().apply {
        add(R.color.label_default)
        add(R.color.label_1)
        add(R.color.label_2)
        add(R.color.label_3)
        add(R.color.label_4)
    }

    val getStatus = mutableListOf<Status>().apply {
        add(Status(id = 0, name = "Todo", color = R.color.priority_default))
        add(Status(id = 0, name = "InProgress", color = R.color.priority_low))
        add(Status(id = 0, name = "Cancelled", color = R.color.priority_medium))
        add(Status(id = 0, name = "Done", color = R.color.priority_high))
    }
}
