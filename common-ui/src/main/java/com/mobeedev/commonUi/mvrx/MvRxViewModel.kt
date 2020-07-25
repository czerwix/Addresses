package com.fieldcode.commonUi.mvrx

import androidx.annotation.VisibleForTesting
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Success
import com.mobeedev.commonDomain.Result
import com.mobeedev.commonDomain.error.EmployeeError
import com.mobeedev.commonDomain.fold
import com.mobeedev.commonDomain.scheduler.DefaultSchedulers
import com.mobeedev.commonDomain.scheduler.Schedulers
import com.mobeedev.commonDomain.usecase.NoParametersUseCase
import com.mobeedev.commonDomain.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

abstract class MvRxViewModel<S : MvRxState>(
    private val initialState: S,
    schedulers: Schedulers = DefaultSchedulers()
) : BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG) {

    protected val viewModelJob = SupervisorJob()

    /**
     * Scope bound to [viewModelJob] which will be cancelled on [onCleared] and run on
     * background thread by default
     */
    protected val backgroundScope = CoroutineScope(viewModelJob + schedulers.background())

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun <T : Any, V, I> UseCase<T, I>.execute(
        params: I,
        mapper: ((T) -> V),
        stateReducer: S.(Async<V>) -> S
    ) {
        setState { stateReducer(Loading()) }
        this.invoke(viewModelJob, params) { result -> handleResult(result, mapper, stateReducer) }
    }

    fun <T : Any, I> UseCase<T, I>.execute(
        params: I,
        stateReducer: S.(Async<T>) -> S
    ) {
        setState { stateReducer(Loading()) }
        this.invoke(viewModelJob, params) { result -> handleResult(result, stateReducer) }
    }

    fun <T : Any, V> NoParametersUseCase<T>.execute(
        mapper: ((T) -> V),
        stateReducer: S.(Async<V>) -> S
    ) {
        setState { stateReducer(Loading()) }
        this.invoke(viewModelJob) { result -> handleResult(result, mapper, stateReducer) }
    }

    fun <T : Any> NoParametersUseCase<T>.execute(
        stateReducer: S.(Async<T>) -> S
    ) {
        setState { stateReducer(Loading()) }
        this.invoke(viewModelJob) { result -> handleResult(result, stateReducer) }
    }

    // TODO: 25/07/2020 implement error handling before setState
    private fun <V> handleError(error: EmployeeError, stateReducer: S.(Async<V>) -> S) {
        setState { stateReducer(Fail(error)) }
    }

    private fun <T : Any, V> handleResult(
        result: Result<T>,
        mapper: ((T) -> V),
        stateReducer: S.(Async<V>) -> S
    ) {
        result.fold(
            error = { handleError(it, stateReducer) },
            success = {
                val success = Success(mapper(it))
                setState { stateReducer(success) }
            })
    }

    private fun <T : Any> handleResult(
        result: Result<T>,
        stateReducer: S.(Async<T>) -> S
    ) {
        result.fold(
            error = { handleError(it, stateReducer) },
            success = {
                val success = Success(it)
                setState { stateReducer(success) }
            })
    }

    fun resetState() = setState { initialState }

    @VisibleForTesting
    fun setTestState(block: S.() -> S) = setState(block)
}
