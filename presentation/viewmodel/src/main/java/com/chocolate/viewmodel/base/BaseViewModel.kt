package com.chocolate.viewmodel.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.NetworkException
import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.exceptions.RateLimitExceededException
import com.chocolate.entities.exceptions.RequestException
import com.chocolate.entities.exceptions.ServerException
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.entities.exceptions.ValidationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, UiEffect>(initialState: STATE) : ViewModel() {
    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialState) }
    val state = _state.asStateFlow()

    protected val _effect = MutableSharedFlow<UiEffect>()
    val effect = _effect.asSharedFlow()

    fun <T> tryToExecute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().also(onSuccess)
            } catch (e: RequestException){
                Log.d("123123123", "tryToExecute: $e 4")
                onError(e)
            }catch (e: RateLimitExceededException){
                Log.d("123123123", "tryToExecute: $e 5")
                onError(e)
            }catch (e: ServerException){
                Log.d("123123123", "tryToExecute: $e 6")
                onError(e)
            }catch (e: NullDataException){
                Log.d("123123123", "tryToExecute: $e 7")
                onError(e)
            }catch (e: ValidationException){
                Log.d("123123123", "tryToExecute: $e 8")
                onError(e)
            }catch (e: TeamixException){
                Log.d("123123123", "tryToExecute: $e 9")
                onError(e)
            }catch (throwable: Throwable) {
                Log.d("123123123", "tryToExecute: $throwable 1")
                onError(throwable)
            }
        }
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        updateState: STATE.(T) -> STATE
    ) {
        viewModelScope.launch {
            flow.collect { value ->
                _state.update { currentState ->
                    currentState.updateState(value)
                }
            }
        }
    }

    protected fun sendUiEffect(effect: UiEffect) {
        viewModelScope.launch(Dispatchers.IO) { _effect.emit(effect) }
    }

}