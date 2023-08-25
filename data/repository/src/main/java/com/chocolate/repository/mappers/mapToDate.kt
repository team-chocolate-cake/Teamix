package com.chocolate.repository.mappers

import java.util.Date

fun Long.toDate() = Date(this*1000L)