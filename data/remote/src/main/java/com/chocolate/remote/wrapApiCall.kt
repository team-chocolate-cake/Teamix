package com.chocolate.remote

import com.chocolate.entities.exceptions.NetworkException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.exceptions.ServerException
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.entities.exceptions.UnAuthorizedException
import com.chocolate.entities.exceptions.UserDeactivatedException
import com.chocolate.repository.utils.HttpStatusCodes
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

suspend fun <T> wrapApiCall(call: suspend () -> Response<T>): T {
    return try {
        val result = call()
        when (result.code()) {
            HttpStatusCodes.BAD_REQUEST.code -> throw NetworkException(result.message())
            HttpStatusCodes.UNAUTHORIZED.code -> throw UnAuthorizedException(result.message())
            HttpStatusCodes.USER_DEACTIVATED.code -> throw UserDeactivatedException(result.message())
            HttpStatusCodes.NOT_FOUND.code -> throw NullDataException(result.message())
            else -> result.body() ?: throw NullDataException(result.message())
        }
    } catch (exception: UnknownHostException) {
        throw NoConnectionException(exception.message)
    } catch (e: SocketTimeoutException) {
        throw ServerException(e.message)
    } catch (e: SSLException) {
        throw ServerException(e.message)
    } catch (e: IOException) {
        throw NetworkException(e.message)
    } catch (e: Exception) {
        throw TeamixException(e.message)
    }
}
