package com.chocolate.repository.repository

import android.os.RemoteException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.exceptions.RateLimitExceededException
import com.chocolate.entities.exceptions.RequestException
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.repository.utils.NullResultException
import com.chocolate.repository.utils.ValidationError
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.repository.utils.NetworkException
import com.chocolate.repository.utils.NotFoundException
import com.chocolate.repository.utils.ServerException
import com.chocolate.repository.utils.TooManyRequestsException
import com.chocolate.repository.utils.UserDeactivatedException

abstract class BaseRepository {
    protected suspend fun <T> wrapCall(response: suspend () -> T): T {
        return try {
            response()
        } catch (e: NetworkException) {
            throw RequestException(e.message)
        } catch (e: NotFoundException) {
            throw NoConnectionException(e.message)
        } catch (e: TooManyRequestsException) {
            throw RateLimitExceededException(e.message)
        } catch (e: UserDeactivatedException) {
            throw ServerException(e.message)
        } catch (e: ValidationError) {
            throw ValidationException(e.message)
        } catch (exception: NullResultException) {
            throw NullDataException(exception.message)
        } catch (exception: RemoteException) {
            throw TeamixException(exception.message.toString())
        }
    }

}