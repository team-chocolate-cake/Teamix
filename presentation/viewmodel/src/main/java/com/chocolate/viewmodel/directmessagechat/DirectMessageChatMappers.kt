package com.chocolate.viewmodel.directmessagechat

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.formatDate(): String {
    val currentTime = Calendar.getInstance().time
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DAY_OF_YEAR, -1)
    val twoDaysAgo = Calendar.getInstance()
    twoDaysAgo.add(Calendar.DAY_OF_YEAR, -2)

    val dateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())

    return when {
        isSameDay(this, currentTime) -> dateFormat.format(this)
        isSameDay(this, yesterday.time) -> "Yesterday"
        this.before(twoDaysAgo.time) -> {
            val dateFormatter = SimpleDateFormat("d/M/yyyy", Locale.getDefault())
            dateFormatter.format(this)
        }
        else -> ""
    }
}

fun isSameDay(date1: Date, date2: Date): Boolean {
    val calendar1 = Calendar.getInstance()
    val calendar2 = Calendar.getInstance()
    calendar1.time = date1
    calendar2.time = date2

    return calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR) &&
            calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
}