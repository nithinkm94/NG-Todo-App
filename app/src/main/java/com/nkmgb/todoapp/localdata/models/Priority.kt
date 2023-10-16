package com.nkmgb.todoapp.localdata.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Priority(
    val id: Int,
    val name: String,
    val color: Int,
    val icon: Int? = null
) : Parcelable
