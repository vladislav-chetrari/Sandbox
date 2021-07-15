package io.sandbox.data.domain

import kotlinx.coroutines.*

//TODO Consider removing
abstract class UseCase<in Input, Output>(private val execute: (Input) -> Output) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun cancel() = job.cancel()

    operator fun invoke(dispatcher: CoroutineDispatcher = Dispatchers.IO, inputParam: Input, consumer: (Result<Output>) -> Unit) {
        scope.launch {
            consumer(Result.Progress)
            try {
                val result = withContext(dispatcher) { execute(inputParam) }
                consumer(Result.Success(result))
            } catch (t: Throwable) {
                consumer(Result.Error(t))
            }
        }
    }
}