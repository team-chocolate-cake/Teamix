package com.chocolate.repository.implementation

import com.chocolate.entities.ErrorType
import com.chocolate.repository.utils.HttpStatusCodes
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseRepository {
    protected suspend fun <T> wrapApiCall(call: suspend () -> Response<T>): T {
        return try {
            val result = call()

            when (result.code()) {
                HttpStatusCodes.BAD_REQUEST.code -> throw ErrorType.Network(result.message())
                HttpStatusCodes.UNAUTHORIZED.code -> throw ErrorType.UnAuthorized(result.message())
                HttpStatusCodes.USER_DEACTIVATED.code -> throw ErrorType.UserDeactivated(result.message())
                HttpStatusCodes.TOO_MANY_REQUESTS.code -> throw ErrorType.RateLimitExceeded(result.message())
                HttpStatusCodes.NO_CONNECTION.code -> throw ErrorType.NoConnection(result.message())

                else -> result.body() ?: throw ErrorType.Unknown(result.message())
            }

        } catch (exception: UnknownHostException) {
            throw ErrorType.UnAuthorized(exception.message.toString())
        } catch (exception: Exception) {
            throw ErrorType.Unknown(exception.message.toString())
        }
    }

}