package com.chocolate.repository.implementation

import com.chocolate.entities.ErrorType
import retrofit2.Response
import java.net.UnknownHostException

interface BaseRepository {
    suspend fun <T> wrapApiCall(call: suspend () -> Response<T>): T {
        return try {
            val result = call()

            when (result.code()) {
                BAD_REQUEST -> throw ErrorType.Network(result.message())
                UNAUTHORIZED -> throw ErrorType.UnAuthorized(result.message())
                USER_DEACTIVATED -> throw ErrorType.UserDeactivated(result.message())
                TOO_MANY_REQUESTS -> throw ErrorType.RateLimitExceeded(result.message())
                NO_CONNECTION -> throw ErrorType.NoConnection(result.message())

                else -> result.body() ?: throw ErrorType.Unknown(result.message())
            }

        } catch (exception: UnknownHostException) {
            throw ErrorType.UnAuthorized(exception.message.toString())
        } catch (exception: Exception) {
            throw ErrorType.Unknown(exception.message.toString())
        }
    }

    companion object{
        private const val BAD_REQUEST = 400
        private const val NO_CONNECTION = 404
        private const val UNAUTHORIZED = 401
        private const val USER_DEACTIVATED = 403
        private const val TOO_MANY_REQUESTS = 429

    }
}