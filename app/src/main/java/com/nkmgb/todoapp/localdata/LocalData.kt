package com.nkmgb.todoapp.localdata

import com.nkmgb.todoapp.R
import com.nkmgb.todoapp.localdata.models.Priority

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
}
