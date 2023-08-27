package com.chocolate.repository.utils

import com.google.gson.Gson

fun Any.toJson() = Gson().toJson(this)