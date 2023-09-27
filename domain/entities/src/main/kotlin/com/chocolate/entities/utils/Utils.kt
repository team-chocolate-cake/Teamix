package com.chocolate.entities.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val String.Companion.Empty get() = ""

fun getRandomId() = System.currentTimeMillis().toInt()

fun Date.toStringDate(): String {
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(this)
}