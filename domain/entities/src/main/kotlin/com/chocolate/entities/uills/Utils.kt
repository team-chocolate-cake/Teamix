package com.chocolate.entities.uills

val String.Companion.Empty get() = ""

fun getRandomId() = System.currentTimeMillis().toInt()