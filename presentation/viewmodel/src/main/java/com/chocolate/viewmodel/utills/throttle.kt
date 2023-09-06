package com.chocolate.viewmodel.utills

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    if (periodMillis < 0) return this
    return flow {
        conflate().collect { value ->
            emit(value)
            delay(periodMillis)
        }
    }
}