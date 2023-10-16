package com.nkmgb.todoapp.ui.widgets

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.util.Calendar
import java.util.Date

fun DatePickerDialog(mContext: Context, onDateChange: (date: String) -> Unit): DatePickerDialog {
// Fetching the Local Context


    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onDateChange("$mYear/${addLeadingZero(mMonth + 1)}/${mDayOfMonth}")
        }, mYear, mMonth, mDay
    )
    return mDatePickerDialog
}

fun addLeadingZero(value : Int) : String{
    return if(value<10){
        "0$value"
    } else "$value"
}
