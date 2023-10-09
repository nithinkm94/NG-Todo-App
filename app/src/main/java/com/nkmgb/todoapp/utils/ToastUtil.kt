package com.nkmgb.todoapp.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

fun Context.toast(message: String): Toast = Toast.makeText(
    this,
    message,
    Toast.LENGTH_SHORT
).apply {
    setGravity(Gravity.TOP, 0, 0)
    show()
}
