package com.chocolate.remote.firebase.util

import com.chocolate.entities.exceptions.NetworkException
import com.chocolate.entities.exceptions.TeamixException
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

suspend fun wrapRealTimeCall(call: suspend () -> Unit) {
    try {
        call()
    } catch (e: CancellationException) {
        throw NetworkException(e.message)
    } catch (io: IOException) {
        throw NetworkException(io.message)
    } catch (e: UnknownHostException) {
        throw NetworkException(e.message)
    } catch (e: Exception) {
        throw TeamixException(e.message)
    }
}

fun getRandomId() = System.currentTimeMillis().toInt()