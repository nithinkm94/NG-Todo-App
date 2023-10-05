package com.nkmgb.todoapp.ui.widgets

import android.app.TimePickerDialog
import android.content.Context
import java.util.Calendar

fun todoTimePickerDialog(
    mContext: Context,
    onTimeChange: (time: String, timeIn24Hours: String) -> Unit
):
        TimePickerDialog {
    val mCurrentTime: Calendar = Calendar.getInstance()
    val hour: Int = mCurrentTime.get(Calendar.HOUR_OF_DAY)
    val minute: Int = mCurrentTime.get(Calendar.MINUTE)
    return TimePickerDialog(
        mContext,
        { _, h, m ->
            onTimeChange(getTimeUnit(h, m), "$h:$m")
        },
        hour,
        minute,
        false
    )
}

fun getTimeUnit(h: Int, m: Int): String {
    var hourMod = h
    var unit = "AM"
    if (h == 0) {
        hourMod = 12
    } else if (h >= 12) {
        if (h > 12) hourMod = h.mod(12)
        unit = "PM"
    }
    return "$hourMod : $m $unit"
}